package com.deepakbaliga.getdone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.baseClasses.GetDoneActivity;
import com.deepakbaliga.getdone.customViews.RegularTextView;
import com.deepakbaliga.getdone.customViews.ThinTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends GetDoneActivity implements Animation.AnimationListener {

    @BindView(R.id.title_textview) ThinTextView titleTextView;
    @BindView(R.id.sub_title_textview) RegularTextView subTitleTextView;
    @BindView(R.id.logo) ImageView splashScreenLogo;

    Animation zoomAnimation;
    Animation fadeInAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);



        zoomAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slow);

        splashScreenLogo.setAnimation(fadeInAnimation);
        titleTextView.setAnimation(fadeInAnimation);
        subTitleTextView.setAnimation(fadeInAnimation);



        zoomAnimation.setAnimationListener(this);




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                splashScreenLogo.setAnimation(zoomAnimation);
                titleTextView.setVisibility(View.INVISIBLE);
                subTitleTextView.setVisibility(View.INVISIBLE);

            }
        }, 2500);





    }

    @Override
    public void onAnimationStart(Animation animation) {


    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
