package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class SplashScreenActivity extends AppCompatActivity {
MediaPlayer mysong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mysong= MediaPlayer.create(SplashScreenActivity.this,R.raw.maa);
        mysong.start();
        Thread theard = new Thread(){
            public void Run(){
                try {
                    sleep(3000);
                }catch (Exception c){

                }
            }
        };
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        },3000);
        theard.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mysong.release();
        finish();
    }
}
