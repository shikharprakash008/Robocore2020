package com.example.robocore;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class sponsors extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        recyclerView=findViewById(R.id.recycler_sponsor);


        //LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_fall_down);
        //recyclerView.setLayoutAnimation(animationController);

RecyclerViewAdaptersponser adaptersponser=new RecyclerViewAdaptersponser();
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(adaptersponser);



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
