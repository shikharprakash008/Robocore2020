package com.example.robocore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.robocore.Reg.memberInfo_form;
import com.example.robocore.Reg.teamLeaderInfo_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class two_member_registration extends AppCompatActivity {

    ImageView iv_back;

    TextInputEditText tiet_teamLeader_name, tiet_teamName, tiet_teamLeader_email, tiet_teamLeader_contact, tiet_teamLeader_college;
    TextInputEditText tiet_member2_name, tiet_member2_email, tiet_member2_contact;

    CardView cvRegister;

    Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_member_registration);

        iv_back = (ImageView) findViewById(R.id.back);

        tiet_teamLeader_name = (TextInputEditText) findViewById(R.id.name1);
        tiet_teamName = (TextInputEditText) findViewById(R.id.teamname);
        tiet_teamLeader_email = (TextInputEditText) findViewById(R.id.email1);
        tiet_teamLeader_contact = (TextInputEditText) findViewById(R.id.contact1);
        tiet_teamLeader_college = (TextInputEditText) findViewById(R.id.college1);

        tiet_member2_name = (TextInputEditText) findViewById(R.id.name2);
        tiet_member2_email = (TextInputEditText) findViewById(R.id.email2);
        tiet_member2_contact = (TextInputEditText) findViewById(R.id.contact2);

        cvRegister = (CardView) findViewById(R.id.cv_register);

        loadingDialog = new Dialog(this);

        final String str_regEvent = getIntent().getStringExtra("event");

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        cvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String str_name1 = tiet_teamLeader_name.getText().toString().trim();
                final String str_email1 = tiet_teamLeader_email.getText().toString().trim();
                final String str_teamName = tiet_teamName.getText().toString().trim();
                final String str_contact1 = tiet_teamLeader_contact.getText().toString().trim();
                final String str_college = tiet_teamLeader_college.getText().toString().trim();

                final String str_name2 = tiet_member2_name.getText().toString().trim();
                String str_email2 = tiet_member2_email.getText().toString().trim();
                String str_contact2 = tiet_member2_contact.getText().toString().trim();

                if (TextUtils.isEmpty(str_name1)) {
                    Toast.makeText(two_member_registration.this, "Enter team leader's name.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_teamName)) {
                    Toast.makeText(two_member_registration.this, "Team name cann't be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_email1)) {
                    Toast.makeText(two_member_registration.this, "Enter team leader's email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_contact1)) {
                    Toast.makeText(two_member_registration.this, "Enter team leader's contact.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_college)) {
                    Toast.makeText(two_member_registration.this, "Enter your college.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isEmpty(str_name2) && (TextUtils.isEmpty(str_email2) || TextUtils.isEmpty(str_contact2))) {
                    Toast.makeText(two_member_registration.this, "Member 2 details are incomplete.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_name2)) {
                    str_email2 = "";
                    str_contact2 = "";
                }

                teamLeaderInfo_form teamLeaderInfo = new teamLeaderInfo_form(str_name1, str_teamName, str_email1, str_contact1, str_college);
                final memberInfo_form memberInfo = new memberInfo_form(str_name2, str_email2, str_contact2);

                DateFormat dftf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
                final String dateTime = dftf.format(Calendar.getInstance().getTime());

                showLoadingDialog(v);

                FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("leaderDetails").setValue(teamLeaderInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(two_member_registration.this, "Leader added successfully.", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference("Registration").child(str_regEvent).child(dateTime).child(str_teamName).child("member2Details").setValue(memberInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(two_member_registration.this, "Registration Successfull.", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                                finish();
                            }
                        });
                        finish();
                    }
                });

                Toast.makeText(two_member_registration.this, str_regEvent, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showLoadingDialog(View v) {

        loadingDialog.setContentView(R.layout.loading_dialogbox);

        Button cover_btn = (Button) loadingDialog.findViewById(R.id.btn_cover);
        ImageView img_loading = (ImageView) loadingDialog.findViewById(R.id.img_loading);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        img_loading.startAnimation(animation);

        cover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(two_member_registration.this, "Registering.", Toast.LENGTH_SHORT).show();
            }
        });

        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.show();

    }


}
