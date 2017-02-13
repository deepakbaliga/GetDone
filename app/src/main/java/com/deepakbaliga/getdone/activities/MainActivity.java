package com.deepakbaliga.getdone.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.adapters.MainViewPagerAdapter;
import com.deepakbaliga.getdone.baseClasses.GetDoneActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends GetDoneActivity {

    @BindView(R.id.home_button) ImageView homeButton;
    @BindView(R.id.projects_button) ImageView projectsButton;
    @BindView(R.id.fab_add) ImageButton floatingActionBar;
    @BindView(R.id.main_pager) ViewPager viewPager;

    private boolean homeTarget = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        ButterKnife.bind(this);
        setButtonProperty();

        initView();

    }

    private void initView() {

        MainViewPagerAdapter pagerAdapter =  new MainViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);

                toggle();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setButtonProperty() {

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!homeTarget)
                    toggle();



            }
        });

        projectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (homeTarget)
                toggle();


            }
        });

    }

    private void toggle() {

        homeTarget = !homeTarget;

        if(homeTarget){
            homeButton.setImageDrawable(getDrawable(R.drawable.home_selected));
            projectsButton.setImageDrawable(getDrawable(R.drawable.project_unselected));

        }

        else{
            homeButton.setImageDrawable(getDrawable(R.drawable.home_unselected));
            projectsButton.setImageDrawable(getDrawable(R.drawable.project_selected));

        }



    }
}
