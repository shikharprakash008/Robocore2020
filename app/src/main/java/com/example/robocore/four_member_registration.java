package com.example.robocore;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.robocore.Reg.memberInfo_form;
import com.example.robocore.Reg.teamLeaderInfo_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class four_member_registration extends AppCompatActivity {

    VideoView videoView;

    ImageView iv_back;

    final int GOOGLE_PAY_REQUEST_CODE = 123;

    String str_teamName;

    teamLeaderInfo_form teamLeaderInfo;
    memberInfo_form member2Info, member3Info, member4Info;

    String str_regEvent;
    String str_eventFee;

    TextInputEditText tiet_teamLeader_name, tiet_teamName, tiet_teamLeader_email, tiet_teamLeader_contact, tiet_teamLeader_college;
    TextInputEditText tiet_member2_name, tiet_member2_email, tiet_member2_contact;
    TextInputEditText tiet_member3_name, tiet_member3_email, tiet_member3_contact;
    TextInputEditText tiet_member4_name, tiet_member4_email, tiet_member4_contact;

    CardView cvRegister;
    Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_member_registration);

        videoView = findViewById(R.id.videoView_bg);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mybg2);
        videoView.setDrawingCacheEnabled(true);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        iv_back = (ImageView) findViewById(R.id.back);


        tiet_teamLeader_name = (TextInputEditText) findViewById(R.id.name1);
        ;
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


        str_regEvent = getIntent().getStringExtra("event");
        str_eventFee = getIntent().getStringExtra("fee");

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
                str_teamName = tiet_teamName.getText().toString().trim();
                final String str_email1 = tiet_teamLeader_email.getText().toString().trim();
                final String str_contact1 = tiet_teamLeader_contact.getText().toString().trim();
                final String str_college = tiet_teamLeader_college.getText().toString().trim();
                final teamLeaderInfo_form four = new teamLeaderInfo_form(str_name1, str_teamName, str_email1, str_contact1, str_college);

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
                if (!fnFirebaseDatabaseRefCheck(str_teamName)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(four_member_registration.this);
                    builder.setMessage("Enter a valid team name.\nTeam name contains only alphabets and digits.")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
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
                member2Info = new memberInfo_form(str_name2, str_email2, str_contact2);
                member3Info = new memberInfo_form(str_name3, str_email3, str_contact3);
                member4Info = new memberInfo_form(str_name4, str_email4, str_contact4);

                showLoadingDialog(v);

                String amounttxt = str_eventFee;
                String notetxt = str_teamName + ": " + str_regEvent;
//                String upitxt = "dawood9055@okhdfcbank";
                String upitxt = "anushant.2k16-1@okaxis";

  // payusingupi(amounttxt, notetxt, upitxt, str_name1);

              fnRegisterTeam();

            }
        });

    }


    private void payusingupi(String amounttxt, String notetxt, String upitxt, String str_name1) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upitxt)
                .appendQueryParameter("pn", str_name1)
                .appendQueryParameter("tn", notetxt)
                .appendQueryParameter("am", amounttxt)
                .appendQueryParameter("cu", "INR")
                .build();


        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case 123:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(four_member_registration.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(four_member_registration.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: "+approvalRefNo);
                fnRegisterTeam();
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(four_member_registration.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
                loadingDialog.dismiss();
            }
            else {
                Toast.makeText(four_member_registration.this, "Transaction failed. Please try again.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
                loadingDialog.dismiss();
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(four_member_registration.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
            loadingDialog.dismiss();
        }
    }

    private void fnRegisterTeam() {

        DateFormat dftf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss:SSS");
       // final  String dateTime=dftf.format();
        final String dateTime = dftf.format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("leaderDetails").setValue(teamLeaderInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("member2Details").setValue(member2Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("member3Details").setValue(member3Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("member4Details").setValue(member4Info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(four_member_registration.this, str_teamName + " registered successfully.", Toast.LENGTH_SHORT).show();
                                        loadingDialog.dismiss();
                                        Intent i = new Intent(four_member_registration.this, qrCode.class);
                                        i.putExtra("timestamp", str_teamName + ": " + dateTime );
                                        i.putExtra("event", str_regEvent);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
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

    @Override
    protected void onResume() {
        videoView.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        videoView.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }

    public boolean fnFirebaseDatabaseRefCheck(String name) {
        return name.matches("[a-zA-Z0-9]+");
    }

}