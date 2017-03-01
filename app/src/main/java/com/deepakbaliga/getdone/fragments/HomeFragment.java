package com.deepakbaliga.getdone.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.adapters.TasksAdapter;
import com.deepakbaliga.getdone.model.Task;
import com.deepakbaliga.getdone.model.TaskDataStructure;
import com.deepakbaliga.getdone.utilities.Constants;
import com.deepakbaliga.getdone.utilities.Pop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.task_recyclerv_view)
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    TasksAdapter adapter;







    /////////////// JUST REALM THINGS ///////////////////
    Realm realm = Realm.getDefaultInstance();
    RealmQuery<Task> query = realm.where(Task.class);
    RealmResults<Task> result;

    ////////////////////////////


    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        result = query.findAllSortedAsync("date", Sort.ASCENDING);

        result.addChangeListener(new RealmChangeListener<RealmResults<Task>>() {
            @Override
            public void onChange(RealmResults<Task> element) {

                adapter = new TasksAdapter(getActivity(), makeList(element));
                recyclerView.setAdapter(adapter);





            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);




        return view;
    }


    private ArrayList<TaskDataStructure> makeList(RealmResults<Task> elements){

        ArrayList<TaskDataStructure> list =  new ArrayList<>();


        //This will be the first ITEM
        Date previousDate = removeTime(elements.get(0).getDate());
        list.add(new TaskDataStructure(Constants.ITEM_DAY, elements.get(0).getDateReadable()));
        list.add(new TaskDataStructure(Constants.ITEM_CARD, elements.get(0)));


        for (int i = 1; i < elements.size(); i++) {

            Date newDate = removeTime(elements.get(i).getDate());

            if(sameDay(previousDate, newDate)){

                list.add(new TaskDataStructure(Constants.ITEM_CARD, elements.get(i)));

            }else {

                list.add(new TaskDataStructure(Constants.ITEM_DAY, elements.get(i).getDateReadable()));
                list.add(new TaskDataStructure(Constants.ITEM_CARD, elements.get(i)));
                previousDate = newDate;

            }
        }

        return list;
    }


    private   Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private boolean sameDay(Date dayONE, Date dayTWO){

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(dayONE);
        cal2.setTime(dayTWO);


        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }




}
