package com.ojasxlabs.cricketlogics;


import android.graphics.Color;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MatchPrediction extends Fragment {
        int i=1;
        //private String team1,team2,winner,date;
        private ListView mListView;
        private ArrayList<String> data;
        Match match;
        private static DatabaseReference db_ref;
        private static FirebaseDatabase db;



         public MatchPrediction() {
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
        View view=inflater.inflate(R.layout.fragment_match_prediction, container, false);
               // setContentView(R.layout.fragment_match_prediction);
                match = new Match();
                db = FirebaseDatabase.getInstance();
                db_ref=db.getReference();
                if(db==null) {

                    db.setPersistenceEnabled(true);
                }



                mListView = (ListView) view.findViewById(R.id.list);
                data = new ArrayList<>();
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,data)
                {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){

                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);

                        // Initialize a TextView for ListView each Item
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        if(position==0 || position==3 || position==6 || position==9 ) {
                            // Set the text color of TextView (ListView Item)
                           tv.setTextColor(Color.WHITE);
                            tv.setBackgroundResource(R.drawable.custim_red_color);
                        }
                        else if (position==2 || position==5 || position==8 || position==11)
                            tv.setTextColor(getResources().getColor(R.color.insta_color));
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
                        //data.add("Predicted Toss Winner : " + match.getToss_winner());
                        data.add("Predicted Winner : " + match.getWinner());

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


            return view;
    }
}
