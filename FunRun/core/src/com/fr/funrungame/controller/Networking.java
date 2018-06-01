package com.fr.funrungame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Controls the networking of the game. It is responsible to read/write from/to the server.
 */
public class Networking {

    private static final String DEFAULT_URL = "https://paginas.fe.up.pt/~up201605646";

    private static final String GET_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/get.php";

    private static final String GET_URL_1 = "https://paginas.fe.up.pt/~up201605646/lpoo/get1.php";

    private static final String SEND_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/insert.php";

    private static final String SEND_URL_1 = "https://paginas.fe.up.pt/~up201605646/lpoo/insert1.php";

    private static final int TIMEOUT = 5;

    private boolean serverResponse1 = false;
    private boolean serverResponse2 = false;

    private ArrayList<Float> times;
    private ArrayList<Float> actions;
    private float best_time;

    public Networking() {}

    private String getTimesString(ArrayList<Float> times) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < times.size(); i++) {
            //sb.append(String.format("%.2f", times.get(i)));
            sb.append(times.get(i));
            sb.append("/");
        }

        return sb.toString();
    }

    private String getActionsString(ArrayList<Float> actions) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < actions.size(); i++) {
            sb.append(actions.get(i));
            sb.append("/");
        }

        return sb.toString();
    }

    public void send(int map, ArrayList<Float> times, ArrayList<Float> actions, float time) {

        ArrayList<Float> left_half = new ArrayList<Float>(times.subList(0, times.size()/2));
        ArrayList<Float> right_half = new ArrayList<Float>(times.subList(times.size()/2, times.size()));

        Map parameters1 = new HashMap();
        parameters1.put("map", String.valueOf(map));
        parameters1.put("time", String.valueOf(time));
        parameters1.put("actions", getActionsString(actions));

        Map parameters2 = new HashMap();
        parameters2.put("map", String.valueOf(map));
        parameters2.put("times", getTimesString(left_half));

        Map parameters3 = new HashMap();
        parameters3.put("map", String.valueOf(map));
        parameters3.put("times", getTimesString(right_half));

        sendHttpRequest(parameters1, SEND_URL);
        sendHttpRequest(parameters2, SEND_URL_1);
        sendHttpRequest(parameters3, SEND_URL_1);
    }

    public void get(int map) {
        serverResponse1 = false;
        serverResponse2 = false;

        Map parameters = new HashMap();
        parameters.put("map", String.valueOf(map));

        sendHttpRequest(parameters, GET_URL);
        sendHttpRequest(parameters, GET_URL_1);
    }

    private void sendHttpRequest(Map parameters, final String url) {
        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(url);
        httpGet.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                String r = httpResponse.getResultAsString();

                if(url.equals(GET_URL)) {
                    parseResponse_Actions(r);
                }
                else if(url.equals(GET_URL_1)) {
                    parseResponse_Times(r);
                }
                else {
                    System.out.println(r);
                }
            }

            @Override
            public void failed(Throwable t) {
                System.out.println("Failed");
            }

            @Override
            public void cancelled() {
                System.out.println("Cancelled");
            }
        });
    }

    private void parseResponse_Actions(String r) {
        actions = new ArrayList<Float>();

        String[] param = r.split(" ");
        best_time = Float.parseFloat(param[1]);
        String[] temp = param[0].split("/");
        for(String i: temp) {
            actions.add(Float.parseFloat(i));
        }
        serverResponse1 = true;
    }

    private void parseResponse_Times(String r) {
        times = new ArrayList<Float>();

        String[] temp = r.split("/");
        for(String i: temp) {
            times.add(Float.parseFloat(i));
        }
        serverResponse2 = true;
    }

    public ArrayList<Float> getTimes() {
        if(waitServerResponse(serverResponse2) == 0) {
            System.out.println("Times: success (x" + times.size() + ")");
            return times;
        }
        System.out.println("Times: insuccess");
        return null;
    }

    public ArrayList<Float> getActions() {
        if(waitServerResponse(serverResponse1) == 0) {
            System.out.println("Actions: success (x" + actions.size() + ")");
            return actions;
        }
        System.out.println("Actions: insuccess");
        return null;
    }

    public float getTime() {
        if(waitServerResponse(serverResponse1) == 0) {
            System.out.println("Time: success (" + best_time + ")");
            return best_time;
        }
        System.out.println("Time: insuccess");
        return -1;
    }

    private int waitServerResponse(boolean f) {
        int i = 0;
        while (!f && i < TIMEOUT ) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

        if(i == TIMEOUT && !f) return 1;
        else return 0;
    }
}
