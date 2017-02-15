package com.deepakbaliga.getdone.activities;


import android.app.SharedElementCallback;
import android.os.Bundle;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.baseClasses.GetDoneActivity;
import com.deepakbaliga.getdone.customViews.RegularButton;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateToDoActivity extends GetDoneActivity {


    @BindView(R.id.button_add_task)
    RegularButton addTaskButton;

    @BindView(R.id.close_button)
    ImageView closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);
        ButterKnife.bind(this);



        init();

    }

    private void init() {

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                CreateToDoActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.close);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        addTaskButton.setText("");
        addTaskButton.setBackground(getDrawable(R.drawable.ripple_rectangle_corners));
    }



}
