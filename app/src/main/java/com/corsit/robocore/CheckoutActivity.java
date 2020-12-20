package com.corsit.robocore;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
EditText  amount,note,name,upi_id;
final int UPI_PAYMENT=0;
Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initializemethod();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amounttxt= amount.getText().toString();
                String notetxt= note.getText().toString();
                String upitxt= upi_id.getText().toString();
                String nametxt= name.getText().toString();
                payusingupi(amounttxt,notetxt,upitxt,nametxt);

            }
        });
    }

    private void payusingupi(String amounttxt, String notetxt, String upitxt, String nametxt) {

        Uri uri = Uri.parse("upi://pay").buildUpon().appendQueryParameter("pa",upitxt)
                .appendQueryParameter("pn",nametxt)
                .appendQueryParameter("tn",notetxt)
                .appendQueryParameter("am",amounttxt)
                .appendQueryParameter("cu","INR").build();

        Intent upi_payment = new Intent(Intent.ACTION_VIEW);
        upi_payment.setData(uri);
        Intent chooser = Intent.createChooser(upi_payment,"pay with");
        if(null!=chooser.resolveActivity(getPackageManager())){
            startActivityForResult(chooser,UPI_PAYMENT);
        }
        else
        {
            Toast.makeText(this,"No upi app found",Toast.LENGTH_SHORT).show();
        }
    }

    private void initializemethod() {

        amount=findViewById(R.id.amount);
        name=findViewById(R.id.name);
        upi_id=findViewById(R.id.upi);
        note=findViewById(R.id.Note);
        pay =findViewById(R.id.button);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case UPI_PAYMENT:
                if((RESULT_OK ==resultCode || (resultCode==11))){
                    if(data!=null){
                        String txt = data.getStringExtra("response");
                        Log.d("UPI","onActivityResult:"+txt);
                        ArrayList<String >dataLst= new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    }
                    else
                    {
                        Log.d("UPI","onActivityResult:"+"Return Data is null");
                        ArrayList<String >dataLst= new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);

                    }
                }
                else
                {
                    Log.d("UPI","onActivityResult:"+"Return Data is null");
                    ArrayList<String >dataLst= new ArrayList<>();
                    dataLst.add("Nothing");
                    upipaymentdataoperation(dataLst);
                }
                break;
        }
    }

    private void upipaymentdataoperation(ArrayList<String> dataLst) {
        if (isConnectionAvailable(CheckoutActivity.this)) {
            String str = dataLst.get(0);
            Log.d("UPIPAY", "upipaymentoperation:" + str);
            String paymentCanel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalref = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("approval Ref".toLowerCase()) ||
                            equalStr[0].toLowerCase().equals("txRef".toLowerCase())) {
                        approvalref = equalStr[1];
                    }

                } else {
                    paymentCanel = "payment cancel by user";
                    if (status.equals("success")) {
                        Toast.makeText(this, "Transaction Succesfull", Toast.LENGTH_SHORT).show();
                        Log.d("UPI", "responsestr+" + approvalref);
                    } else if ("Payment cancel by user".equals(paymentCanel)) {
                        Toast.makeText(this, "Payment cancel by user", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }


        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isConnected()&&networkInfo.isConnectedOrConnecting()&& networkInfo.isAvailable()){
                return true;
            }

        }
        return false;
    }

}