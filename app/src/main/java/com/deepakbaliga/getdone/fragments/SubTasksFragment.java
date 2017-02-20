package com.deepakbaliga.getdone.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.activities.CreateToDoActivity;
import com.deepakbaliga.getdone.adapters.SubTaskAdapter;
import com.deepakbaliga.getdone.callback.SimpleItemTouchHelperCallback;
import com.deepakbaliga.getdone.customViews.ThinEditTextView;
import com.deepakbaliga.getdone.model.SubTask;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubTasksFragment extends Fragment {


    @BindView(R.id.sub_task_recycler_view)
     RecyclerView subTasksRecyclerView;

    @BindView(R.id.sub_task_edit_text)
     ThinEditTextView subTaskEditText;

    @BindView(R.id.add_sub_task_button)
     ImageButton addTaskButton;

    private LinearLayoutManager linearLayoutManager;
    private LinkedList<SubTask> subTasks = new LinkedList<>();
    private SubTaskAdapter subTaskAdapter;

    private ItemTouchHelper mItemTouchHelper;



    public SubTasksFragment() {
        // Required empty public constructor
    }

    public static SubTasksFragment getInstance(){

        SubTasksFragment subTasksFragment = new SubTasksFragment();
        return  subTasksFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_sub_tasks, container, false);
        ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        subTasksRecyclerView.setLayoutManager(linearLayoutManager);

        loadData();



        subTaskAdapter = new SubTaskAdapter(getActivity(), subTasks);
        subTasksRecyclerView.setAdapter(subTaskAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(subTaskAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(subTasksRecyclerView);


        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(subTaskEditText.getText().toString().length()>0){

                    subTasks.add(new SubTask((long)Math.random()*100,subTaskEditText.getText().toString()));
                    subTaskAdapter.notifyDataSetChanged();
                    subTaskEditText.setText("");
                }

            }
        });







        return view;
    }

    private void loadData() {

        subTasks.add(new SubTask(1,"Take Lucy for a walk"));
        subTasks.add(new SubTask(1,"Throw the dustbin items"));
        subTasks.add(new SubTask(1,"Get clothes from laundry"));
        subTasks.add(new SubTask(1,"Buy gifts for John"));
        subTasks.add(new SubTask(1,"Order Cake for John's Birthday"));

    }



}
