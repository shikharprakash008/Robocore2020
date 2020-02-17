package com.example.robocore;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class NavigationDrawer extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    VideoView videoView;
    ScrollView scrollView;

    LinearLayout countDownLlayout;
    AnimationDrawable animationDrawable;
    TextView tv;
    CountdownView mCvCountdownView;
    ActionBarDrawerToggle toggle;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);



        drawerLayout= findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        initImageBitmaps();
        navigationView.setNavigationItemSelectedListener(this);

        /*videoView = findViewById(R.id.mVideoView);
        setSupportActionBar(toolbar);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg);
        videoView.setDrawingCacheEnabled(true);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();*/

        //code for count dime timer

        //scrollView= (ScrollView) findViewById(R.id.scroll);

        //animationDrawable = (AnimationDrawable) scrollView.getBackground();

        //animationDrawable.setEnterFadeDuration(4500);
       // animationDrawable.setExitFadeDuration(4500);
        //animationDrawable.start();


        mCvCountdownView = findViewById(R.id.mycountdown);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String countDate = "06-03-2020 00:00:00";
        Date now = new Date();


        try {
            //Formatting from String to Date
            Date date = sdf.parse(countDate);
            long currentTime = now.getTime();
            long newYearDate = date.getTime();
            long countDownToNewYear = newYearDate - currentTime;
            mCvCountdownView.start(countDownToNewYear);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        int id = menuitem.getItemId();

        Intent i;

        switch (id) {
            case R.id.events:
                i= new Intent(NavigationDrawer.this,Events.class);
                startActivity(i);
                break;
            case R.id.team:
                i= new Intent(NavigationDrawer.this,Team.class);
                startActivity(i);
                break;
            case R.id.contact:
                i= new Intent(NavigationDrawer.this,ConatactUs.class);
                startActivity(i);
                break;
            case R.id.Location:
                Uri gmmIntentUri = Uri.parse("geo:13.3269° N, 77.1261° E");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                    finish();
                }
                break;
            case R.id.sponsors:
                i= new Intent(NavigationDrawer.this,sponsors.class);
                startActivity(i);
                break;
            case R.id.item_robocor:

            case R.id.item_projects:

            case R.id.item_workshop:

            case R.id.item_competitions:

            case R.id.item_sessions:
                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationDrawer.this);
                builder.setMessage("You do not have access to it!")
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.about_corsit:
                startActivity(new Intent(NavigationDrawer.this, aboutPopUp.class));
                break;
            case R.id.about_robocor:
                startActivity(new Intent(NavigationDrawer.this, aboutPopUpRobocor.class));
                break;

        }

        drawerLayout.closeDrawer((GravityCompat.START));
        return true;
    }
    private void initImageBitmaps(){
        mImageUrls.add(R.mipmap.lfrone);
        mNames.add("Line Follower");
        mImageUrls.add(R.mipmap.robo);
        mNames.add("Cross Roads");
        mImageUrls.add(R.mipmap.roborace);
        mNames.add("RoboRash");
        mImageUrls.add(R.mipmap.ppr);
        mNames.add("Paper");
        mImageUrls.add(R.mipmap.robosoccer);
        mNames.add("Robo Soccer");
        mImageUrls.add(R.mipmap.coding);
        mNames.add("D-Code");
        mImageUrls.add(R.drawable.arduino);
        mNames.add("Arduino Clash");


        initRecyclerView();
    }

   private void initRecyclerView() {
       LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       RecyclerView recyclerView = findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(layoutManager);
       RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames,mImageUrls);
       recyclerView.setAdapter(adapter);

   }
}
