package com.example.robocore;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    List<Model> models;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        models = new ArrayList<>();
        models.add(new Model(R.mipmap.lfr, "Situation 2.0", "Fumble and it’s all over!! Trace it and track the prize!!! This event is for the code masters. Here’s your chance to prove your robot’s worth. A series of simple yet complex path are to be traced by the autonomous robot. Build the right circuit and test the precision of your programming. And as the saying goes, “You don’t fall out of track and you shall emerge victorious\", stay on line and pave the path to success!!! Registration Fee : Rs 400/- Per Team\n"));
        models.add(new Model(R.mipmap.robo, "Rugged Rage", "Design a wired robot within the specified dimensions that can be operated manually and can travel through all turns of the track adorned with mud, speed breakers, hurdles, bumps and a lot more from the starting position to the finish, without going outside the track. Hit the roads full-throttle , hit the top gear and take the fastlane to glory. Registration Fee : Rs 400/- Per Team\n"));
        models.add(new Model(R.mipmap.crossroads, "Cross Roads", "Gear up for rumbling engines, flamboyant wireless cars and adrenaline packed races at the all new Cross - Roads . It's all about speed, control and accuracy, the bot that covers the track in the minimum time collecting the maximum points will be crowned the champion. Registration Fee : Rs 300/- Per Team\n"));
        models.add(new Model(R.mipmap.soccer, "Robo Soccer", "The objective is to design a manual robot which can compete on an arena specially designed for robotic soccer match. It will be a one vs one soccer match in which the aim will be to push the ball in the opponent's goal post. The most dexterous and quirky design prevail.Registration Fee : Rs 400/- Per Team\n"));
        models.add(new Model(R.drawable.paper, "Paper Presentation", "Putting pen to paper and allowing your mind to speak is what adds to the glory of technical event. It's all about how innovative your idea is, how much you've researched on it and how you present it. Topics can be on engineering and high tech purposes, describing advances in technology, problem resolution, or open innovation . All papers must be strictly in IEEE format . Abstract submission open, mail the abstracts to \"corsit.sit@gmail.com\" . Last date to submit the abstract 3rd March. Registration Fee : Rs 150/- Per Team\n"));
        models.add(new Model(R.mipmap.decode, "D-Code", "Coding is one of the most important part of Robotics. This event encourages students from all branches to participate and improve their coding and critical thinking skills. Bring your id with you and you are good to go! Registration Fee : Rs 100/- in a Team of Two\n"));
        models.add(new Model(R.mipmap.duno, "Arduino Clash", "Unleash your potential . Move over to the harder levels and get your brain to sweat a little. It's a brainstorming challenge where you are supposed to do instant projects on arduino. First round involves MCQ's based on general technical knowledge and in the second round selected teams will be given challenges along with required components to complete the on spot task using arduino. Registration Fee : Rs 200/- in a Team of Three\n"));
        models.add(new Model(R.mipmap.project, "Project Symposium", "Are you ready to show your skills? A Platform to build, develop and showcase your ideas. SWEAT YOUR MIND AND WORK TO INNOVATE THE FUTURE! Registration Fee : Rs 400/- Per Team\n"));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        viewPager.setPadding(130, 0, 130, 0);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int x = viewPager.getCurrentItem() + 1;
                Intent i;

                switch (x) {
                    case 1:
                        i = new Intent(Events.this, four_member_registration.class);
                        i.putExtra("event", "Situation");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(Events.this, four_member_registration.class);
                        i.putExtra("event", "CrossRoads");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(Events.this, four_member_registration.class);
                        i.putExtra("event", "RuggedRage");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(Events.this, two_member_registration.class);
                        i.putExtra("event", "PaperPresentation");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 5:
                        i = new Intent(Events.this, four_member_registration.class);
                        i.putExtra("event", "RoboSoccer");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 6:
                        i = new Intent(Events.this, two_member_registration.class);
                        i.putExtra("event", "DCode");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 7:
                        i = new Intent(Events.this, three_member_registration.class);
                        i.putExtra("event", "ArduinoClash");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    case 8:
                        i = new Intent(Events.this, four_member_registration.class);
                        i.putExtra("event", "ProjectSymposium");
                        i.putExtra("fee", "1");
                        startActivity(i);
                        break;
                    default:
                        Toast.makeText(Events.this, "Sonething went wrong.", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


        Integer[] colors_temp = {
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
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
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
