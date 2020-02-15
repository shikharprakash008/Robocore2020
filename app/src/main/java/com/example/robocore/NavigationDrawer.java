package com.example.robocore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class NavigationDrawer extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    VideoView videoView;
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        int id = menuitem.getItemId();

        if (id == R.id.corsit) {
            Intent i= new Intent(NavigationDrawer.this,AboutCorsit.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.robocore) {
            Intent i= new Intent(NavigationDrawer.this,AboutRobocore.class);
            startActivity(i);
        } else if (id == R.id.events) {
            Intent i= new Intent(NavigationDrawer.this,Events.class);
            startActivity(i);
        } else if (id == R.id.team) {
            Intent i= new Intent(NavigationDrawer.this,Team.class);
            startActivity(i);
        }
        else if (id == R.id.contact) {
            Intent i= new Intent(NavigationDrawer.this,ConatactUs.class);
            startActivity(i);
        }
        else if (id==R.id.Location)
        {
            Uri gmmIntentUri = Uri.parse("geo:13.3269° N, 77.1261° E");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
                finish();
            }
        }
        else if (id==R.id.sponsors)
        {
            Intent i= new Intent(NavigationDrawer.this,sponsors.class);
            startActivity(i);
        }

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
        mNames.add("Decode");

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
