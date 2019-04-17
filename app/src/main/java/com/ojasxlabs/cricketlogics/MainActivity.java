package com.ojasxlabs.cricketlogics;


import android.graphics.Color;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    int i=1;
    private String team1,team2,winner,date;
    private ListView mListView;
    private ArrayList<String> data;
    Match match;
    private static DatabaseReference db_ref;
    private static FirebaseDatabase db;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        match = new Match();
        db = FirebaseDatabase.getInstance();
        db_ref=db.getReference();
       if(db==null) {

            db.setPersistenceEnabled(true);
       }




        MobileAds.initialize(this, "ca-app-pub-4327627819814341~7447187245");



        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("9762F7D37EE798D43AF75C9C8573B8ED").build();
        mAdView.loadAd(adRequest);



        mListView = (ListView) findViewById(R.id.list);
        data = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    TextView tv = (TextView) view.findViewById(android.R.id.text1);
                if(position==0 || position==3 || position==6 || position==9 ) {
                    // Set the text color of TextView (ListView Item)
                    tv.setTextColor(Color.RED);
                }
                else if (position==2 || position==5 || position==8 || position==11)
                    tv.setTextColor(Color.BLUE);
                // Generate ListView Item using TextView
                return view;
            }


        };

db_ref.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        //for(DataSnapshot ds : dataSnapshot.getChildren()){

            match = dataSnapshot.getValue(Match.class);
            data.add("Match "+ (i++) + " : " + match.getDate());
            data.add(match.getTeam1() + " vs " + match.getTeam2());
            data.add("Winner : " + match.getWinner());

       // }
        mListView.setAdapter(arrayAdapter);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

    }
}
