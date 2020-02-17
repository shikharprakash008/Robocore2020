package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class aboutPopUpRobocor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pop_up_robocor);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        JustifyTextView jtv = (JustifyTextView) findViewById(R.id.text);

        YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(jtv);

        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.55));

    }
}
