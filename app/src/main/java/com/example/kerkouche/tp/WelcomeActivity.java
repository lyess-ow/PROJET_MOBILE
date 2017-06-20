package com.example.kerkouche.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class WelcomeActivity extends AwesomeSplash {

    //private static int SPLASH_TIME_OUT = 2000;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    } */

    @Override
    public void initSplash(ConfigSplash configSplash) {


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Background animation
        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(0); //3000
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);

        //LOGO
        configSplash.setLogoSplash(R.drawable.logo1);
        configSplash.setAnimLogoSplashDuration(0);//5000
        configSplash.setAnimLogoSplashTechnique(Techniques.ZoomIn);

        //Titre
        configSplash.setTitleSplash(getString(R.string.string25));
        configSplash.setTitleTextColor(R.color.colorPrimary);
        configSplash.setTitleTextSize(50f);
        configSplash.setAnimTitleDuration(0);//3000
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
        finish();
    }
}

