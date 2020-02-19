package com.example.robocore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
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

import com.example.robocore.Reg.memberInfo_form;
import com.example.robocore.Reg.teamLeaderInfo_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class two_member_registration extends AppCompatActivity {

    VideoView videoView;

    ImageView iv_back;

    String str_teamName;

    teamLeaderInfo_form teamLeaderInfo;
    memberInfo_form memberInfo;

    String str_regEvent;

    TextInputEditText tiet_teamLeader_name, tiet_teamName, tiet_teamLeader_email, tiet_teamLeader_contact, tiet_teamLeader_college;
    TextInputEditText tiet_member2_name, tiet_member2_email, tiet_member2_contact;

    CardView cvRegister;

    Dialog loadingDialog;

    final int UPI_PAYMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_member_registration);

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
        tiet_teamName = (TextInputEditText) findViewById(R.id.teamname);
        tiet_teamLeader_email = (TextInputEditText) findViewById(R.id.email1);
        tiet_teamLeader_contact = (TextInputEditText) findViewById(R.id.contact1);
        tiet_teamLeader_college = (TextInputEditText) findViewById(R.id.college1);

        tiet_member2_name = (TextInputEditText) findViewById(R.id.name2);
        tiet_member2_email = (TextInputEditText) findViewById(R.id.email2);
        tiet_member2_contact = (TextInputEditText) findViewById(R.id.contact2);

        cvRegister = (CardView) findViewById(R.id.cv_register);

        loadingDialog = new Dialog(this);

        str_regEvent = getIntent().getStringExtra("event");

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
                str_teamName = tiet_teamName.getText().toString().trim();
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

                teamLeaderInfo = new teamLeaderInfo_form(str_name1, str_teamName, str_email1, str_contact1, str_college);
                memberInfo = new memberInfo_form(str_name2, str_email2, str_contact2);

                DateFormat dftf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss.SSS");
                final String dateTime = dftf.format(Calendar.getInstance().getTime());

                showLoadingDialog(v);

                String amounttxt = "1";
                String notetxt = str_teamName + ": " + str_regEvent;
                String upitxt = "dawood9055@okhdfcbank";
//                String upitxt = "kaisarshabirdar@oksbi";
//                String upitxt = "8296668642@paytm";

                payusingupi(amounttxt, notetxt, upitxt, str_name1);

//                fnRegisterTeam();

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

        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(two_member_registration.this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }

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
            case UPI_PAYMENT:
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
        if (isConnectionAvailable(two_member_registration.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(two_member_registration.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: " + approvalRefNo);
                fnRegisterTeam();
            } else if ("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(two_member_registration.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: " + approvalRefNo);
                loadingDialog.dismiss();
            } else {
                Toast.makeText(two_member_registration.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: " + approvalRefNo);
                loadingDialog.dismiss();
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(two_member_registration.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
            loadingDialog.dismiss();
        }
    }

    private void fnRegisterTeam() {

        DateFormat dftf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss:SSS");
        final String dateTime = dftf.format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("leaderDetails").setValue(teamLeaderInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseDatabase.getInstance().getReference("Registrations").child(str_regEvent).child(dateTime).child(str_teamName).child("member2Details").setValue(memberInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(two_member_registration.this, str_teamName + "registered successfully.", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                        Intent i = new Intent(two_member_registration.this, qrCode.class);
                        i.putExtra("timestamp", str_teamName + ": " + dateTime );
                        i.putExtra("event", str_regEvent);
                        startActivity(i);
                        finish();
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


}
