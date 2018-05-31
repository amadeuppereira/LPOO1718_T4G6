package com.fr.funrungame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Networking {

    private static final String DEFAULT_URL = "https://paginas.fe.up.pt/~up201605646";

    private static final String GET_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/get.php";

    private static final String INSERT_URL = "https://paginas.fe.up.pt/~up201605646/lpoo/insert.php";

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


}
