package com.posicionamientointerno.appprincipal.externalsystem;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


public class ExternalSystemLoginUser {


    public final static String apiURL = "http://10.0.2.2:8181/SistemaPosicionamientoInterno.Web/api/Login/SignIn";
    public static final String LOGIN_PREFERENCES = "lOGIN";
    public static final String LOGIN_PREFERENCES_USER_TOKEN= "TOKEN";
    private Context context;

    public void logInExternalSystem( JSONObject jsonParams){
    try {

        StringEntity entity = new StringEntity(jsonParams.toString());
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(null, apiURL, entity, "application/json", new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject obj = new JSONObject(responseString);
                    JSONObject data =(JSONObject)obj.get("Data");
                    setToken(data.getString("Token"));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("failureeeeeeee");
            }
        });

    } catch (Exception e){
        System.out.println(e);
    }
    }

    public ExternalSystemLoginUser(Context context) {
        this.context=context;
    }


    public void setToken(String token) {
        SharedPreferences sp = context.getSharedPreferences(LOGIN_PREFERENCES, 1);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString(LOGIN_PREFERENCES_USER_TOKEN, token);
        Ed.commit();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /*
* JSONObject obj = new JSONObject(responseString);
                   JSONObject data =(JSONObject)obj.get("Data");
                   setToken(data.getString("Token"));
* */
}

