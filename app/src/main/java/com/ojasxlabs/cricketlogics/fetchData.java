package com.ojasxlabs.cricketlogics;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://cricapi.com/api/matches/zosrgfZiaKfwBkGII6SY5UiNYXR2");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "Id: "+ JO.get("unique_id") + "\n"+
                        "Date:" + JO.get("date") + "\n"+
                        "Date in GMT:" + JO.get("dateTimeGMT") + "\n"+
                        "Team 1:" + JO.get("team-1") + "\n"+
                        "Team 2:" + JO.get("team-2") + "\n"+
                        "Type of Match:" + JO.get("type") + "\n" +
                        "Squad:" + JO.get("squad") + "\n"+
                        "Toss Winner:" + JO.get("toss_winner_team") + "\n"+
                        "Match Winner:" + JO.get("winner_team") + "\n"+
                        "Match Started? :" + JO.get("matchStarted") + "\n";

                dataParsed = dataParsed + singleParsed +"\n" ;


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
       //AllMatches.data.setText(this.dataParsed);

    }
}
