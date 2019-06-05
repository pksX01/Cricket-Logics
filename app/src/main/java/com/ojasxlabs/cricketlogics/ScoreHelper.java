package com.ojasxlabs.cricketlogics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreHelper extends AppCompatActivity {
   /* private static String url = "http://cricapi.com/api/cricketScore/?apikey=zosrgfZiaKfwBkGII6SY5UiNYXR2&unique_id=";
    TextView textScore ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_helper);
        Intent intent = getIntent();
        String matchId = intent.getStringExtra("uniqueId");
        textScore = (TextView) findViewById(R.id.score);
        url = url + matchId;
        getScore();
    }

    public void getScore(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String score = jsonObject.getString("score");
                            textScore.setText("Score : " + score);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(ScoreHelper.this, e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ScoreHelper.this, error.getMessage(),Toast.LENGTH_LONG).show();
                        pDialog.dismiss();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }*/
}

