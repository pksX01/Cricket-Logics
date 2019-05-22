package com.ojasxlabs.cricketlogics;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


public class AllMatches extends Fragment {

    private String TAG = AllMatches.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String url = "http://cricapi.com/api/matches/zosrgfZiaKfwBkGII6SY5UiNYXR2";

    ArrayList<HashMap<String, String>> matchList;

    public AllMatches() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_matches, container, false);
        matchList = new ArrayList<>();

        lv = (ListView) view.findViewById(R.id.list);

        new GetMatches().execute();
        return view;
    }

    private class GetMatches extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            if (pDialog==null)
                pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray matches = jsonObj.getJSONArray("matches");

                    // looping through All Contacts
                    for (int i = 0; i < 1; i++) {
                        // try {
                        JSONObject c = matches.getJSONObject(i);
                        final String id = c.getString("unique_id");
                       /*getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
                            }
                        });*/
                        final String dateTimeGMT = c.getString("dateTimeGMT");
                        /*getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), dateTimeGMT, Toast.LENGTH_SHORT).show();
                            }
                        });*/
                            /*SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            format1.setTimeZone(TimeZone.getTimeZone(dateTimeGMT));
                            Date date = format1.parse(dateTimeGMT);
                            SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            format2.setTimeZone(TimeZone.getTimeZone("GMT"));
                            String dateTime = format2.format(date);*/
                        final String team1 = c.getString("team-1");
                        /*getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                        Toast.makeText(getActivity(), team1, Toast.LENGTH_SHORT).show();
                             }
                        });*/
                           final String team2 = c.getString("team-2");
                        /*getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(getActivity(), team2, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });*/
                          final   String typeOfMatch = c.getString("type");
                        /*getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(getActivity(), typeOfMatch, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });*/
                            String squad = c.getString("squad");
                       // Toast.makeText(getActivity(),squad ,Toast.LENGTH_SHORT).show();
                            String toss_winner = c.getString("toss_winner_team");
                       // Toast.makeText(getActivity(),toss_winner ,Toast.LENGTH_SHORT).show();
                            String winner_team = c.getString("winner_team");
                      // Toast.makeText(getActivity(),winner_team ,Toast.LENGTH_SHORT).show();
                            String matchStarted = c.getString("matchStarted");
                       // Toast.makeText(getActivity(),matchStarted ,Toast.LENGTH_SHORT).show();


                            // tmp hash map for single contact
                            final HashMap<String, String> matchMap = new HashMap<>();

                            // adding each child node to HashMap key => value
                            matchMap.put("Date:", dateTimeGMT);
                            matchMap.put("Team 1:", team1);
                            matchMap.put("Team 2:", team2);
                            matchMap.put("Type of Match:", typeOfMatch);
                            matchMap.put("Squad :", squad);
                            matchMap.put("Toss Winner", toss_winner);
                            matchMap.put("Match Winner", winner_team);
                            matchMap.put("Match Started? :", matchStarted);

                            // adding contact to contact list
                            matchList.add(matchMap);
                        getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(getActivity(),matchMap.get("Team 1"),Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                        /*}
                        catch (Exception e){
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }*/
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), matchList,
                    R.layout.matches_list, new String[]{"date", "team1",
                    "team2", "typeOfMatch", "squad", "toss_winner", "winner-team", "matchStarted"}, new int[]{R.id.date,
                    R.id.team1, R.id.team2, R.id.matchType, R.id.squad ,R.id.tossWinner, R.id.winner, R.id.matchStarted});

            lv.setAdapter(adapter);
        }

    }
}
