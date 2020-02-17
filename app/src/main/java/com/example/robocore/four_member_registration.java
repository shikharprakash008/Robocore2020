package com.example.robocore;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.robocore.Reg.memberInfo_form;
import com.example.robocore.Reg.teamLeaderInfo_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class four_member_registration extends AppCompatActivity {

    ImageView iv_back;
    FirebaseDatabase database;
    DatabaseReference registration;

    TextInputEditText tiet_teamLeader_name, tiet_teamName, tiet_teamLeader_email, tiet_teamLeader_contact, tiet_teamLeader_college;
    TextInputEditText tiet_member2_name, tiet_member2_email, tiet_member2_contact;
    TextInputEditText tiet_member3_name, tiet_member3_email, tiet_member3_contact;
    TextInputEditText tiet_member4_name, tiet_member4_email, tiet_member4_contact;

    CardView cvRegister;
    FirebaseAuth mAuth;
    Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_member_registration);


        mAuth = FirebaseAuth.getInstance();
        iv_back = (ImageView) findViewById(R.id.back);

//        database = FirebaseDatabase.getInstance();
//        registration = database.getReference("Registration");
        // users=database.getReference("Users");

        tiet_teamLeader_name = (TextInputEditText) findViewById(R.id.name1);
        tiet_teamName = (TextInputEditText) findViewById(R.id.teamname);
        tiet_teamLeader_email = (TextInputEditText) findViewById(R.id.email1);
        tiet_teamLeader_contact = (TextInputEditText) findViewById(R.id.contact1);
        tiet_teamLeader_college = (TextInputEditText) findViewById(R.id.college1);

        tiet_member2_name = (TextInputEditText) findViewById(R.id.name2);
        tiet_member2_email = (TextInputEditText) findViewById(R.id.email2);
        tiet_member2_contact = (TextInputEditText) findViewById(R.id.contact2);

        tiet_member3_name = (TextInputEditText) findViewById(R.id.name3);
        tiet_member3_email = (TextInputEditText) findViewById(R.id.email3);
        tiet_member3_contact = (TextInputEditText) findViewById(R.id.contact3);

        tiet_member4_name = (TextInputEditText) findViewById(R.id.name4);
        tiet_member4_email = (TextInputEditText) findViewById(R.id.email4);
        tiet_member4_contact = (TextInputEditText) findViewById(R.id.contact4);

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
                final String str_teamName = tiet_teamName.getText().toString().trim();
                final String str_email1 = tiet_teamLeader_email.getText().toString().trim();
                final String str_contact1 = tiet_teamLeader_contact.getText().toString().trim();
                final String str_college = tiet_teamLeader_college.getText().toString().trim();
                final teamLeaderInfo_form four = new teamLeaderInfo_form(str_name1, str_teamName, str_email1, str_contact1, str_college);
//
//
//                registration.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.child(four.getTeamLeader_name()).exists())
//                            Toast.makeText(four_member_registration.this,"This name already exist",Toast.LENGTH_SHORT).show();
//                        else {
//                            registration.child(four.getTeamLeader_name()).setValue(four);
//                            loadingDialog.dismiss();
//                            Toast.makeText(four_member_registration.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//
//
//                mAuth.createUserWithEmailAndPassword(str_teamName, str_college).addOnCompleteListener(four_member_registration.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if(task.isSuccessful()){
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(four_member_registration.this, "You have been successfully registered", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(four_member_registration.this, MainActivity.class));
//                        } else {
//
//                            Toast.makeText(four_member_registration.this, "Error in registry", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//            }
//        });

//
//
                String str_name2 = tiet_member2_name.getText().toString().trim();
                String str_email2 = tiet_member2_email.getText().toString().trim();
                String str_contact2 = tiet_member2_contact.getText().toString().trim();

                String str_name3 = tiet_member3_name.getText().toString().trim();
                String str_email3 = tiet_member3_email.getText().toString().trim();
                String str_contact3 = tiet_member3_contact.getText().toString().trim();

                String str_name4 = tiet_member4_name.getText().toString().trim();
                String str_email4 = tiet_member4_email.getText().toString().trim();
                String str_contact4 = tiet_member4_contact.getText().toString().trim();


                if (TextUtils.isEmpty(str_name1)) {
                    Toast.makeText(four_member_registration.this, "Enter team leader's name.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_teamName)) {
                    Toast.makeText(four_member_registration.this, "Team name cann't be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_email1)) {
                    Toast.makeText(four_member_registration.this, "Enter team leader's email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_contact1)) {
                    Toast.makeText(four_member_registration.this, "Enter team leader's contact.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_college)) {
                    Toast.makeText(four_member_registration.this, "Enter your college.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isEmpty(str_name2) && (TextUtils.isEmpty(str_email2) || TextUtils.isEmpty(str_contact2))) {
                    Toast.makeText(four_member_registration.this, "Member 2 details are incomplete.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_name2)) {
                    str_email2 = "";
                    str_contact2 = "";
                }
                if (!TextUtils.isEmpty(str_name3) && (TextUtils.isEmpty(str_email3) || TextUtils.isEmpty(str_contact3))) {
                    Toast.makeText(four_member_registration.this, "Member 3 details are incomplete.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_name3)) {
                    str_email3 = "";
                    str_contact3 = "";
                }

                if (!TextUtils.isEmpty(str_name4) && (TextUtils.isEmpty(str_email4) || TextUtils.isEmpty(str_contact4))) {
                    Toast.makeText(four_member_registration.this, "Member 4 details are incomplete.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(str_name4)) {
                    str_email4 = "";
                    str_contact4 = "";
                }

                //firebase


                teamLeaderInfo_form teamLeaderInfo = new teamLeaderInfo_form(str_name1, str_teamName, str_email1, str_contact1, str_college);
                final memberInfo_form member2Info = new memberInfo_form(str_name2, str_email2, str_contact2);
                final memberInfo_form member3Info = new memberInfo_form(str_name3, str_email3, str_contact3);
                final memberInfo_form member4Info = new memberInfo_form(str_name4, str_email4, str_contact4);
                //  final teamLeaderInfo_form teamLeaderInfoForm =new teamLeaderInfo_form(str_name1,str_email1,str_college,str_contact1,str_college);

                DateFormat dftf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
                final String dateTime = dftf.format(Calendar.getInstance().getTime());

                showLoadingDialog(v);

                FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("leaderDetails").setValue(member2Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(four_member_registration.this, "Leader added successfully.", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference("Registration").child(str_regEvent).child(dateTime).child(str_teamName).child("member2Details").setValue(member2Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseDatabase.getInstance().getReference("Registration").child(str_regEvent).child(dateTime).child(str_teamName).child("member3Details").setValue(member3Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FirebaseDatabase.getInstance().getReference("Registration").child(str_regEvent).child(dateTime).child(str_teamName).child("member4Details").setValue(member4Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(four_member_registration.this, "Registration Successfull.", Toast.LENGTH_SHORT).show();
                                                loadingDialog.dismiss();
                                                finish();
                                            }
                                        });

                                    }
                                });
                            }
                        });
                    }
                });

                Toast.makeText(four_member_registration.this, str_regEvent, Toast.LENGTH_SHORT).show();

            }
        });

    }

    //
    public void showLoadingDialog(View v) {

        loadingDialog.setContentView(R.layout.loading_dialogbox);

        Button cover_btn = (Button) loadingDialog.findViewById(R.id.btn_cover);
        ImageView img_loading = (ImageView) loadingDialog.findViewById(R.id.img_loading);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        img_loading.startAnimation(animation);

        cover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(four_member_registration.this, "Registering.", Toast.LENGTH_SHORT).show();
            }
        });

        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.show();

    }

}
