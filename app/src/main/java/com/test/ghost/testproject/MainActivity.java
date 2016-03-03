package com.test.ghost.testproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String TAG = "matata:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        new PostAsync().execute();

    }

    class PostAsync extends AsyncTask<String, Integer, String> {

        private ProgressDialog pd;

        protected void onPreExecute() {
            //pd = ProgressDialog.show(MainActivity.this, null, "Loading...");
        }

        protected String doInBackground(String... params) {

            String targetUrl = "http://bebetrack.com/api/create";

            JSONObject postObj = new JSONObject();
            try {
                postObj = new JSONObject();
                postObj.accumulate("phone" ,"1246");
                postObj.accumulate("internationalCode" ,"16");

            }catch (JSONException e){

            }

            String response = Net.excutePostJson(targetUrl,postObj);

            return response;
        }

        protected void onPostExecute(String response) {

            //pd.dismiss();
            if (response == null) {
                Toast.makeText(MainActivity.this, "Sorry! Error", Toast.LENGTH_LONG).show();
                return;
            }
            try {

                JSONObject resObj = new JSONObject(response);
                Toast.makeText(MainActivity.this,resObj.getString("pin"), Toast.LENGTH_LONG).show();
            }catch (JSONException e){

            }

            Log.d(TAG, "response" + response);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
