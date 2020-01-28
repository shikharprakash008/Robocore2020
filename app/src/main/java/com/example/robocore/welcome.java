package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        imageview=(ImageView)findViewById(R.id.imageView);
        textview=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.btn);
        imageview.startAnimation(smalltobig);
        textview.startAnimation(nothingtocome);
        btn.startAnimation(btnanim);

    }
}