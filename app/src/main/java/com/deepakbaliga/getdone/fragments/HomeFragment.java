package com.deepakbaliga.getdone.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.model.Task;
import com.deepakbaliga.getdone.utilities.Pop;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.task_recyclerv_view)
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;







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




        result = query.findAllAsync();

        result.addChangeListener(new RealmChangeListener<RealmResults<Task>>() {
            @Override
            public void onChange(RealmResults<Task> element) {

                for(Task task : element){

                    Log.e("Task", task.getTask());
                }


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

}
