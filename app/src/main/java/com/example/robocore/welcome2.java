package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome2 extends AppCompatActivity {
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        btn1 = findViewById(R.id.btn21);
        btn2 =findViewById(R.id.btn22);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcome();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcome3();
            }
        });


    }

    public  void openwelcome(){
        Intent intent = new Intent(this,welcome.class);
        startActivity(intent);

    }

    public  void  openwelcome3(){

        Intent intent = new Intent(this,welcome3.class);
        startActivity(intent);


    }


}
