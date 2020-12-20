package com.corsit.robocore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AboutCorsit extends AppCompatActivity {
ImageView image2;
private RecyclerView recyclerview;
private RecyclerView recyclerview2;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls=new ArrayList<>();

private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_corsit);
        image2=findViewById(R.id.back);
        recyclerview=findViewById(R.id.recyclerView);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AboutCorsit.this,NavigationDrawer.class);
                startActivity(back);
            }
        });
initImageBitmaps();


    }
    private void initImageBitmaps(){
        mImageUrls.add(R.mipmap.robo_tech);
        mNames.add("Robocon");
        mImageUrls.add(R.mipmap.rc);
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

