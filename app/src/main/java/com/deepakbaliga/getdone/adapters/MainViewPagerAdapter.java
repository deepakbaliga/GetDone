package com.deepakbaliga.getdone.adapters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.deepakbaliga.getdone.fragments.HomeFragment;
import com.deepakbaliga.getdone.fragments.ProjectsFragment;

/**
 * Created by deepakbaliga on 13/02/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return HomeFragment.newInstance();


            case 1:
                return ProjectsFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
