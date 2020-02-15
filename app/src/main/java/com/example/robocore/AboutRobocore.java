package com.example.robocore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AboutRobocore extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls=new ArrayList<>();
ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_robocore);
        image2=findViewById(R.id.back);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(AboutRobocore.this,NavigationDrawer.class);
                startActivity(i);
                initImageBitmaps();
            }
        });


    }
    private void initImageBitmaps(){
        mImageUrls.add(R.mipmap.lfrone);
        mNames.add("Robocon");
        mImageUrls.add(R.mipmap.robo);
        mNames.add("Projects");
        mImageUrls.add(R.mipmap.roborace);
        mNames.add("Workshop");
        mImageUrls.add(R.mipmap.ppr);
        mNames.add("Competitions");
        mImageUrls.add(R.mipmap.robosoccer);
        mNames.add("Session");


        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        AboutAdapter adapter = new AboutAdapter(this,mNames,mImageUrls);
        recyclerView.setAdapter(adapter);

    }







}

