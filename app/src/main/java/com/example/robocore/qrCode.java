package com.example.robocore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrCode extends AppCompatActivity {

    VideoView videoView;

    String TAG = "GenerateQRCode";
    String eventName;
    ImageView qrImage;
    String inputValue;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        eventName = getIntent().getStringExtra("event");
        inputValue = getIntent().getStringExtra("timestamp");

        switch (eventName) {
            case "Situation":
                eventName = "Situation 2.0";
                break;
            case "CrossRoads":
                eventName = "Cross Roads";
                break;
            case "RuggedRage":
                eventName = "Rugged Rage";
                break;
            case "PaperPresentation":
                eventName = "Paper Presentation";
                break;
            case "RoboSoccer":
                eventName = "Robo Soccer";
                break;
            case "DCode":
                eventName = "D-Code";
                break;
            case "ArduinoClash":
                eventName = "Arduino Clash";
                break;
            case "ProjectSymposium":
                eventName = "Project Symposium";
                break;
            default:
                Toast.makeText(qrCode.this, "Sonething went wrong.", Toast.LENGTH_SHORT).show();
        }

        tv = (TextView) findViewById(R.id.tv_event);

        tv.setText(eventName);

        videoView = findViewById(R.id.videoView_bg);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mybg);
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


        qrImage = (ImageView) findViewById(R.id.image);

        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrImage.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        } else {

        }

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
