package com.example.robocore;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity {

    Animation topanim,bottomanim;
    ImageView image;
    TextView logo;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        topanim= AnimationUtils.loadAnimation(this,R.anim.splashanimtop);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.splashanimbottom);
        image = findViewById(R.id.corsitlogo);
        logo = findViewById(R.id.splashcorsit);
        progress = findViewById(R.id.splashprogress);
        image.setAnimation(topanim);
        logo.setAnimation(bottomanim);
        progress.setAnimation(bottomanim);
        final Intent intent = new Intent(this,NavigationDrawer.class);
Thread timer = new Thread(){
    public void run(){
        try{


            sleep(3000);
        }catch (InterruptedException e){

            e.printStackTrace();
        }
        finally {
            startActivity(intent);
            finish();
        }




    }
};


timer.start();

    }
}
