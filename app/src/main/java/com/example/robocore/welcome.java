package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class welcome extends AppCompatActivity {
    Animation smalltobig,nothingtocome,btnanim;
    ImageView imageview;
    TextView textview;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        smalltobig= AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        nothingtocome=AnimationUtils.loadAnimation(this,R.anim.nothingtocome);
        btnanim=AnimationUtils.loadAnimation(this,R.anim.btnanim);
        imageview=findViewById(R.id.imageview);
        textview=findViewById(R.id.textview);
        btn=findViewById(R.id.btn2);
        imageview.startAnimation(smalltobig);
        textview.startAnimation(nothingtocome);
        btn.startAnimation(btnanim);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcome2();
            }
        });


    }

    public void openwelcome2(){
        Intent intent = new Intent(this,welcome2.class);
        startActivity(intent);



    }



}
