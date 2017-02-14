package com.deepakbaliga.getdone.activities;


import android.os.Bundle;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.baseClasses.GetDoneActivity;

public class CreateToDoActivity extends GetDoneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
