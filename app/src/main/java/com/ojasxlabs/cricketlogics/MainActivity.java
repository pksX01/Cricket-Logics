package com.ojasxlabs.cricketlogics;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    /*int i=1;
    private String team1,team2,winner,date;
    private ListView mListView;
    private ArrayList<String> data;
    Match match;
    private static DatabaseReference db_ref;
    private static FirebaseDatabase db;*/

    private AdView mAdView;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713 ");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()/*.addTestDevice("9762F7D37EE798D43AF75C9C8573B8ED")*/.build();
        mAdView.loadAd(adRequest);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /* match = new Match();
                db = FirebaseDatabase.getInstance();
                db_ref=db.getReference();
               if(db==null) {

                    db.setPersistenceEnabled(true);
               }*/
       /* mListView = (ListView) findViewById(R.id.list);
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
            });*/

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WorldCupScheduleFragment(), "World Cup Schedule");
        adapter.addFragment(new MatchPrediction(), "Match Prediction");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
