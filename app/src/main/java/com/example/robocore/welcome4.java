package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome4 extends AppCompatActivity {
Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome4);

        btn1= findViewById(R.id.btn41);
        btn2=findViewById(R.id.btn42);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcome3();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainactivity();
            }
        });

    }
    public void openwelcome3(){
        Intent intent = new Intent(this,welcome3.class);
        startActivity(intent);
    }
    public void  openmainactivity(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
