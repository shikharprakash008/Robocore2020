package com.example.robocore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class sponsors extends AppCompatActivity {
 private  RecyclerView recyclerView;
 private  RecyclerView.Adapter adapter;
 ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        recyclerView=findViewById(R.id.recycler_sponsor);
        recyclerView.setHasFixedSize(true);
        image2=findViewById(R.id.backspn);

        //LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_fall_down);
        //recyclerView.setLayoutAnimation(animationController);


recyclerView.setLayoutManager(new LinearLayoutManager(this));
adapter = new myadaptersponser2(Data.givedata(),this);
recyclerView.setAdapter(adapter);

       // image2=findViewById(R.id.back);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(sponsors.this,NavigationDrawer.class);
                startActivity(i);

            }
        });


    }


    private void runLayoutAnimation(RecyclerView recyclerView){


        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController=
                AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }




}
