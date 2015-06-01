package com.posicionamientointerno.appprincipal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.posicionamientointerno.appprincipal.externalsystem.ExternalSystemLoginUser;
import com.posicionamientointerno.appprincipal.util.Utility;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    public static final String LOGIN_PREFERENCES = "lOGIN";
    public static final String LOGIN_PREFERENCES_USER_FIELD = "USER";
    public static final String LOGIN_PREFERENCES_USER_PASSWORD = "PASSWORD";
    public static final String LOGIN_PREFERENCES_USER_TOKEN= "TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp1=this.getSharedPreferences(LOGIN_PREFERENCES,1);
        String user=sp1.getString(LOGIN_PREFERENCES_USER_FIELD, null);
        setContentView(R.layout.activity_main);
       /* if (user!=null){
            Intent intent = new Intent(this, MainActivityUser.class);
            startActivity(intent);
        }*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick_Login(View v) {


        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        JSONObject jsonParams =new JSONObject();

        if(Utility.isNotNull(username.getText().toString())){
            try {
                jsonParams.put("UserName", username.getText().toString());
                jsonParams.put("Password", password.getText().toString());
                ExternalSystemLoginUser externalSystem = new ExternalSystemLoginUser(this);
                externalSystem.logInExternalSystem(jsonParams);
                SharedPreferences sp = getSharedPreferences(LOGIN_PREFERENCES, 1);
                SharedPreferences.Editor Ed = sp.edit();
                Ed.putString(LOGIN_PREFERENCES_USER_FIELD, username.getText().toString());
                Ed.putString(LOGIN_PREFERENCES_USER_PASSWORD, password.getText().toString());
                Ed.commit();
                Intent intent = new Intent(this, MainActivityUser.class);
                startActivity(intent);

            }
            catch (Exception e){

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
