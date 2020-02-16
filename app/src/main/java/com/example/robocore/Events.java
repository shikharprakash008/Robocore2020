package com.example.robocore;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    List<Model> models;
    ArgbEvaluator argbEvaluator =new ArgbEvaluator();
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        models=new ArrayList<>();
        models.add(new Model(R.drawable.lfr,"Line Follower","Line follower is an autonomous robot that can detect and follow the line drawn on the floor. Generally, the path is predefined and can be visible like a black line on a white surface with a high contrasted color. Participants should have to complete the paths as per rules and regulations in shortest time, taking the shortest path.  Registration Fee : Rs 400 Per Team"));
        models.add(new Model(R.drawable.roborace,"RoboRace","Design a wired robot within the specified dimensions that can be operated manually and can travel through all turns of the track adorned with mud, speed breakers, hurdles, bumps and a lot more from the starting position to the finish, without going outside the track. Hit the roads full-throttle , hit the top gear and take the fastlane to glory.  Registration Fee : Rs 300 Per Team"));
        models.add(new Model(R.drawable.cross,"Cross Roads","Gear up for rumbling engines, flamboyant wireless cars and adrenaline packed races at the all new Cross - Roads . It's all about speed, control and accuracy, the bot that covers the track in the minimum time collecting the maximum points will be crowned the champion.  Registration Fee : Rs 300 Per Team"));
        models.add(new Model(R.drawable.roborace,"Robo Soccer","The objective is to design a manual robot which can compete on an arena specially designed for robotic soccer match. It will be a one vs one soccer match in which the aim will be to push the ball in the opponent's goal post. The most dexterous and quirky design prevail.  Registration Fee : Rs 300 Per Team"));
        models.add(new Model(R.drawable.paper,"Paper Presentation","Putting pen to paper and allowing your mind to speak is what adds to the glory of technical event. It's all about how innovative your idea is, how much you've researched on it and how you present it. Topics can be on engineering and high tech purposes, describing advances in technology, problem resolution, or open innovation . All papers must be strictly in IEEE format .  Abstract submission open, mail the abstracts to \\\"corsit.sit@gmail.com\\\" .   Last date to submit the abstract 12th March.  Registration Fee : Rs 150 Per Team"));
        models.add(new Model(R.drawable.decode_projecte,"D-Code","Coding is one of the most important part of Robotics. This event encourage students from all branches to participate and improve their coding and critical thinking skills. Bring your id with you and you are good to go!"));
        models.add(new Model(R.drawable.arduino,"Duino Ninja",""));

        adapter = new Adapter (models,this);

        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        viewPager.setPadding(130,0,130,0);
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int x= viewPager.getCurrentItem()+1;

                if (x==1){
                    Intent i= new Intent(Events.this,CheckoutActivity.class);
                    startActivity(i);
                }



            }
        });


        Integer[] colors_temp ={
          getResources().getColor(R.color.pink_one),
                getResources().getColor(R.color.robosoccer),
                getResources().getColor(R.color.graylight),
                getResources().getColor(R.color.robosoccer),
                getResources().getColor(R.color.paper),
                getResources().getColor(R.color.graylight),
                getResources().getColor(R.color.arduino),
        };
        colors = colors_temp;



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position<(adapter.getCount()-1) && position <(colors.length-1)){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,colors[position],colors[position+1]));
                }
                else {
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}
