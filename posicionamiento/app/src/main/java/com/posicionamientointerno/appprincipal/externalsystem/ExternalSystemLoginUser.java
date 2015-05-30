package com.posicionamientointerno.appprincipal.externalsystem;


import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ExternalSystemLoginUser {


    public final static String apiURL = "http://localhost/SistemaPosicionamientoInterno.Web/api/Login/SignIn";

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void logInExternalSystem(RequestParams params){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(apiURL,params, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject obj = new JSONObject(responseString);
                    System.out.println(obj.toString());
                } catch (Exception e){
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }

    public ExternalSystemLoginUser() {
    }
}

