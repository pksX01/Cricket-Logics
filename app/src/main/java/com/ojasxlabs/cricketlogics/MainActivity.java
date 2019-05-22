package com.ojasxlabs.cricketlogics;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
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
import com.google.firebase.messaging.FirebaseMessaging;

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
    public static final String CHANNEL_ID = "Cricket_Logics";
    public static final String CHANNEL_NAME = "Cricket Logics";
    public static final String CHANNEL_DESC = "Cricket Logics Notification";
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FirebaseMessaging.getInstance().subscribeToTopic("Updates");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WorldCupScheduleFragment(), "World Cup Schedule");
        adapter.addFragment(new MatchPrediction(), "Match Prediction");
        //adapter.addFragment(new AllMatches(), "Upcoming Matches");
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
