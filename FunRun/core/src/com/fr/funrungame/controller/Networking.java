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

    /**
     * Server's url to get the best time and the actions of a given map.
     */
    private static final String GET_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/get.php";
    /**
     * Server's url to get the actions corresponding times.
     */
    private static final String GET_URL_1 = "https://paginas.fe.up.pt/~up201605646/lpoo/get1.php";
    /**
     * Server's url to send a map best time and actions.
     */
    private static final String SEND_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/insert.php";
    /**
     * Server's url send actions corresponding times.
     */
    private static final String SEND_URL_1 = "https://paginas.fe.up.pt/~up201605646/lpoo/insert1.php";
    /**
     * Max time to get a server response.
     */
    private static final int TIMEOUT = 5;
    /**
     * Flag for server response.
     */
    private boolean serverResponse1 = false;
    /**
     * Flag for server response.
     */
    private boolean serverResponse2 = false;

    /**
     * The times of the actions that made a map best time.
     */
    private ArrayList<Float> times;
    /**
     * Map actions that made a map best time.
     */
    private ArrayList<Float> actions;
    /**
     * Map best time.
     */
    private float best_time;

    /**
     * Creates a new Networking object that handles all the server accesses.
     */
    public Networking() {}

    /**
     * Changes an array into a server friendly string
     *
     * @param times array with the information
     * @return the array corresponding string
     */
    private String getString(ArrayList<Float> times) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < times.size(); i++) {
            sb.append(times.get(i));
            sb.append("/");
        }

        return sb.toString();
    }

    /**
     * Sends a new record to the server
     *
     * @param map the map in which the record was achieved
     * @param times the times when the player made the actions
     * @param actions the player actions
     * @param time the new map record
     */
    public void send(int map, ArrayList<Float> times, ArrayList<Float> actions, float time) {

        ArrayList<Float> left_half = new ArrayList<Float>(times.subList(0, times.size()/2));
        ArrayList<Float> right_half = new ArrayList<Float>(times.subList(times.size()/2, times.size()));

        Map parameters1 = new HashMap();
        parameters1.put("map", String.valueOf(map));
        parameters1.put("time", String.valueOf(time));
        parameters1.put("actions", getString(actions));

        Map parameters2 = new HashMap();
        parameters2.put("map", String.valueOf(map));
        parameters2.put("times", getString(left_half));

        Map parameters3 = new HashMap();
        parameters3.put("map", String.valueOf(map));
        parameters3.put("times", getString(right_half));

        sendHttpRequest(parameters1, SEND_URL);
        sendHttpRequest(parameters2, SEND_URL_1);
        sendHttpRequest(parameters3, SEND_URL_1);
    }

    /**
     * Gets a map record from the server.
     *
     * @param map map which record is wanted
     */
    public void get(int map) {
        serverResponse1 = false;
        serverResponse2 = false;

        Map parameters = new HashMap();
        parameters.put("map", String.valueOf(map));

        sendHttpRequest(parameters, GET_URL);
        sendHttpRequest(parameters, GET_URL_1);
    }

    /**
     * Sends a request to the server and handles the response.
     *
     * @param parameters the request parameters
     * @param url the request url
     */
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

    /**
     * Parses a string from a server response with the best time and the actions into the corresponding variables.
     *
     * @param r string to parse
     */
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

    /**
     * Parses a string from a server response with times of the actions.
     *
     * @param r string to parse
     */
    private void parseResponse_Times(String r) {
        times = new ArrayList<Float>();

        String[] temp = r.split("/");
        for(String i: temp) {
            times.add(Float.parseFloat(i));
        }
        serverResponse2 = true;
    }

    /**
     * Gets the map record actions' times.
     *
     * @return actions' times
     */
    public ArrayList<Float> getTimes() {
        if(waitServerResponse(serverResponse2) == 0) {
            System.out.println("Times: success (x" + times.size() + ")");
            return times;
        }
        System.out.println("Times: insuccess");
        return null;
    }

    /**
     * Get the map record actions.
     *
     * @return actions
     */
    public ArrayList<Float> getActions() {
        if(waitServerResponse(serverResponse1) == 0) {
            System.out.println("Actions: success (x" + actions.size() + ")");
            return actions;
        }
        System.out.println("Actions: insuccess");
        return null;
    }

    /**
     * Get the map record time.
     *
     * @return
     */
    public float getTime() {
        if(waitServerResponse(serverResponse1) == 0) {
            System.out.println("Time: success (" + best_time + ")");
            return best_time;
        }
        System.out.println("Time: insuccess");
        return -1;
    }

    /**
     * Waits for a server response.
     *
     * @param f flag to check
     * @return 0 if server responded, 1 otherwise
     */
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
