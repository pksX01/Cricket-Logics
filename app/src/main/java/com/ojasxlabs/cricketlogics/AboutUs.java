package com.ojasxlabs.cricketlogics;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static android.text.Html.fromHtml;

public class AboutUs extends AppCompatActivity {
    private ImageView mailButton;
    private Toolbar toolbar;

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(fromHtml("<font color='#ffffff'>About Us</font>"));
        //getActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mailButton = (ImageView) findViewById(R.id.mail);
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.setType("text/email");
                mIntent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"pksx01@gmail.com"});
                mIntent.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                mIntent.putExtra(Intent.EXTRA_TEXT, "Dear NeuralX Labs," + "");  //Email 's Greeting text
                if (mIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(mIntent, "Send Email:"));
                } else {
                    Toast.makeText(AboutUs.this, "There is no app support this action", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
