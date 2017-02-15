package com.deepakbaliga.getdone.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.adapters.CategoriesAdapter;
import com.deepakbaliga.getdone.model.Category;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsFragment extends Fragment {

    @BindView(R.id.categories_recyclerview) RecyclerView categoryRecyclerView;


    private GridLayoutManager gridLayoutManager;
    private CategoriesAdapter categoriesAdapter;
    private LinkedList<Category> categories = new LinkedList<>();

    public ProjectsFragment() {






    }

    public static ProjectsFragment newInstance(){
        return new ProjectsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);

        categories.add(new Category((long)(Math.random()*1000000),"Personal", 43, R.drawable.icon_person, R.color.alizarin,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Work", 235, R.drawable.icon_work, R.color.amethyst,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Meet", 3, R.drawable.icon_happy_mood, R.color.peter_river,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Home", 113, R.drawable.icon_home, R.color.emerald,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"School", 23, R.drawable.icon_school, R.color.sun_flower,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Earth", 0, R.drawable.icon_earth, R.color.green_sea,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Universe", 7, R.drawable.icon_star, R.color.clouds,false,0));


        gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayout.VERTICAL, false);
        categoriesAdapter = new CategoriesAdapter(getActivity(),categories);
        categoryRecyclerView.setLayoutManager(gridLayoutManager);
        categoryRecyclerView.setAdapter(categoriesAdapter);



        return view;
    }

}
