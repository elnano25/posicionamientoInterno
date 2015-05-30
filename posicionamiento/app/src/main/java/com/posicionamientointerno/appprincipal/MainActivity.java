package com.posicionamientointerno.appprincipal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public static final String LOGIN_PREFERENCES = "lOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp1=this.getSharedPreferences(LOGIN_PREFERENCES,1);
        String user=sp1.getString("User", null);
        setContentView(R.layout.activity_main);
        if (user!=null){
            Intent intent = new Intent(this, MainActivityUser.class);
            startActivity(intent);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick_Login(View v) {
        //call web services
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        SharedPreferences sp=getSharedPreferences(LOGIN_PREFERENCES, 1);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("Unm",username.getText().toString() );
        Ed.putString("Psw",password.getText().toString());
        Ed.commit();
        Intent intent = new Intent(this, MainActivityUser.class);
        startActivity(intent);
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
