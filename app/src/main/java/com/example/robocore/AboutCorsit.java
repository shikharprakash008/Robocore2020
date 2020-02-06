package com.example.robocore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AboutCorsit extends AppCompatActivity {
ImageView image2;
private RecyclerView recyclerview;
private RecyclerView recyclerview2;
private int[] images= {R.drawable.logo,R.drawable.instagram
};
private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_corsit);
        image2=findViewById(R.id.back);
        recyclerview=findViewById(R.id.recyclerView);
        recyclerview2=findViewById(R.id.recyclerView2);
        adapter=new RecyclerAdapter(images);
        recyclerview.setAdapter(adapter);
        recyclerview2.setAdapter(adapter);
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
