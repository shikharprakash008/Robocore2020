package com.example.robocore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutRobocore extends AppCompatActivity {
ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_robocore);
        image2=findViewById(R.id.back);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });


    }
    public  void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);





    }
}
