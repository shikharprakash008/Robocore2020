package com.corsit.robocore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;

public class ConatactUs extends AppCompatActivity {

    TextInputEditText tiev_email, tiev_name;
    EditText et_sub, et_msg;
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conatact_us);


        cv = (CardView) findViewById(R.id.cardView);
        tiev_name = (TextInputEditText) findViewById(R.id.name);
        tiev_email = (TextInputEditText) findViewById(R.id.mail);
        et_sub = (EditText) findViewById(R.id.subject);
        et_msg = (EditText) findViewById(R.id.msg);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, email, sub, msg;

                name = tiev_name.getText().toString().trim();
                email = tiev_email.getText().toString().trim();
                sub = et_sub.getText().toString().trim();
                msg = et_msg.getText().toString().trim();

                String myMailId = "corsit.sit@gmail.com";
                String[] emails = myMailId.split(","); //split not required since only one email id

                msg = msg + "\n\"" + "Email: " + email + "\"" + "\n\"" + "Name: " + name + "\"";

                Intent sendMail = new Intent(Intent.ACTION_SEND);


                sendMail.putExtra(Intent.EXTRA_EMAIL, emails);
                sendMail.putExtra(Intent.EXTRA_SUBJECT, sub);
                sendMail.putExtra(Intent.EXTRA_TEXT, msg);
                sendMail.setType("message/rfc822");
                sendMail.setPackage("com.google.android.gm");

                if(name.isEmpty()) {
                    Toast.makeText(ConatactUs.this, "Enter a valid name.", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()) {
                    Toast.makeText(ConatactUs.this, "Enter a valid email.", Toast.LENGTH_SHORT).show();
                }
                else if(sub.isEmpty()) {
                    Toast.makeText(ConatactUs.this, "Enter a valid subject.", Toast.LENGTH_SHORT).show();
                }
                else if(msg.isEmpty()) {
                    Toast.makeText(ConatactUs.this, "Enter message.", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(sendMail);
                }

            }
        });

    }
}
