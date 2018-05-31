package com.fr.funrungame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Networking {

    private static final String DEFAULT_URL = "https://paginas.fe.up.pt/~up201605646";

    private static final String GET_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/get.php";

    private static final String SEND_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/insert.php";

    private static final int TIMEOUT = 5;

    boolean serverResponse = false;

    public Networking() {}

    public int checkNetworkConnection() {

        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(DEFAULT_URL);
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                serverResponse = true;
            }

            @Override
            public void failed(Throwable t) {
                serverResponse = true;
            }

            @Override
            public void cancelled() {
                serverResponse = true;
            }
        });

        int i = 0;
        while(!serverResponse && i < TIMEOUT) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
        }

        if(i == TIMEOUT) return 1;
        else return 0;
    }

    private String getTimesString(ArrayList<Float> times) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < times.size(); i++) {
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

        Map parameters1 = new HashMap();
        parameters1.put("map", String.valueOf(map));
        parameters1.put("time", String.valueOf(time));
        parameters1.put("actions", getActionsString(actions));

        Map parameters2 = new HashMap();
        parameters2.put("map", String.valueOf(map));
        parameters2.put("times", getTimesString(times));

        sendHttpRequest(parameters1, SEND_URL);
        sendHttpRequest(parameters2, SEND_URL);
    }

    private void sendHttpRequest(Map parameters, String url) {
        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(url);
        httpGet.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                System.out.println("Sent");
                System.out.println(httpResponse.getResultAsString());
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

    private void parseResponse(String r) {
        ArrayList<Float> times = new ArrayList<Float>();
        ArrayList<Float> actions = new ArrayList<Float>();

        String[] param = r.split(" ");
        float best_time = Float.parseFloat(param[0]);

        String[] temp = param[1].split("/");
        for(String i: temp) {
            times.add(Float.parseFloat(i));
        }

        temp = param[2].split("/");
        for(String i: temp) {
            actions.add(Float.parseFloat(i));
        }
    }




}
