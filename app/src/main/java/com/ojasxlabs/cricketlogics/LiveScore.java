package com.ojasxlabs.cricketlogics;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import am.appwise.components.ni.NoInternetDialog;


public class LiveScore extends Fragment {
    private static final String url = "http://cricapi.com/api/matches/zosrgfZiaKfwBkGII6SY5UiNYXR2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItemForLiveScore> listItems;
    String matchStatus;
    String dateTime;
    String formattedDate;
    NoInternetDialog noInternetDialog;
    public LiveScore() {
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
        View view = inflater.inflate(R.layout.fragment_live_score, container, false);
        noInternetDialog = new NoInternetDialog.Builder(getContext()).build();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();
        loadRecyclerViewData();
        
        return view;
    }

    public void loadRecyclerViewData(){
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray( "matches");

                            for(int i=0; i<array.length();i++){
                                JSONObject o = array.getJSONObject(i);
                                String matchStarted = o.getString("matchStarted");
                                if(matchStarted.equals("true"))
                                    matchStatus="Match started";
                                else
                                    matchStatus = "Match is not started yet";

                                dateTime = o.getString("date");
                                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy ");
                                try {
                                    Date date = inputFormat.parse(dateTime);
                                    formattedDate = outputFormat.format(date);
                                }
                                catch (Exception e){
                                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                }

                                ListItemForLiveScore item = new ListItemForLiveScore(
                                        //o.getString("unique_id"),
                                        //o.getString("date"),
                                        formattedDate,
                                        //o.getString("squad"),
                                        o.getString("team-2"),
                                        o.getString("team-1"),
                                        matchStatus
                                );

                                listItems.add(item);
                            }

                            adapter = new AdapterForLiveScore(listItems,getActivity());
                            recyclerView.setAdapter(adapter);
                        }
                       catch (JSONException e){
                            e.printStackTrace();
                       }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(),Toast.LENGTH_LONG).show();
                        pDialog.dismiss();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    @Override
    public  void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}
