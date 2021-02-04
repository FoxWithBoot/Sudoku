package com.example.kur;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreenActivity extends Activity {

    GifImageView gif;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        gif = findViewById(R.id.gifka);
        anim = AnimationUtils.loadAnimation(this, R.anim.appear);
        gif.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                anim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.disappear);
                gif.startAnimation(anim);
            }
        }, 2500);

        new Thread() {
            @Override
            public void run() {
                try{
                    Thread.sleep(4900);
                    finish();
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
