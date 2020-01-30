package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome3 extends AppCompatActivity {
Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);
        btn1=findViewById(R.id.btn31);
        btn2=findViewById(R.id.btn32);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcome2();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcome4();
            }
        });

    }
    public void openwelcome2(){
        Intent intent = new Intent(this,welcome2.class);
        startActivity(intent);
    }
    public void openwelcome4(){
        Intent intent = new Intent(this,welcome4.class);
        startActivity(intent);

    }
}
