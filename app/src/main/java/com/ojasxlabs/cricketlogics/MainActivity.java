package com.ojasxlabs.cricketlogics;


import android.graphics.Color;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    long no_of_matches;
    int i=1;
    private String team1,team2,winner,date;
    private ListView mListView;
    private ArrayList<String> data= new ArrayList<>();

    private static DatabaseReference db_ref;
    private static FirebaseDatabase db;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-4327627819814341~7447187245");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       if(db==null) {
            db = FirebaseDatabase.getInstance();
            db.setPersistenceEnabled(true);
        }
        db_ref=db.getReference();
        mListView = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data){

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
        mListView.setAdapter(arrayAdapter);

        db_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                no_of_matches = dataSnapshot.getChildrenCount();

                //Toast.makeText(getApplicationContext(),"no of matches = "+ no_of_matches,Toast.LENGTH_LONG).show();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    team1=ds.child("Team 1").getValue(String.class);
                    team2=ds.child("Team 2").getValue(String.class);
                    winner=ds.child("Winner").getValue(String.class);
                    date=ds.child("Date").getValue(String.class);

                    data.add("Match "+ (i++) + " : "+date);
                    //text.setTextColor(Color.BLACK);
                    data.add(team1+" vs "+team2);
                    //text.setTextColor(Color.GREEN);
                    data.add("Winner : " + winner);
                    //data.add(team1+" "+team2+" "+winner+" "+date);
                    //data.add(team2);

                    //Toast.makeText(getApplicationContext(),team1+" "+team2+" "+winner+" "+date+" ",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

   /* @Override
    public void onStart(){
        super.onStart();


    }*/
}
