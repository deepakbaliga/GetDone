package com.deepakbaliga.getdone.fragments;


import android.app.FragmentManager;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.activities.CreateToDoActivity;
import com.deepakbaliga.getdone.adapters.SubTaskAdapter;
import com.deepakbaliga.getdone.callback.CallBackSubTasks;
import com.deepakbaliga.getdone.callback.ItemCallBack;
import com.deepakbaliga.getdone.callback.SimpleItemTouchHelperCallback;
import com.deepakbaliga.getdone.customViews.ThinEditTextView;
import com.deepakbaliga.getdone.model.SubTask;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

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
    private static LinkedList<SubTask> subTasks = new LinkedList<>();
    private SubTaskAdapter subTaskAdapter;

    private ItemTouchHelper mItemTouchHelper;

    private static CallBackSubTasks callBackSubTasks;





    public SubTasksFragment() {
        
    }

    public static SubTasksFragment getInstance(LinkedList<SubTask> subTasksList, CallBackSubTasks backSubTasks){


        subTasks = subTasksList;
        callBackSubTasks = backSubTasks;

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


            subTaskEditText.post(new Runnable() {
                @Override
                public void run() {
                    subTaskEditText.requestFocus();
                    InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imgr.showSoftInput(subTaskEditText, InputMethodManager.SHOW_IMPLICIT);
                }
            });


        subTaskAdapter = new SubTaskAdapter(getActivity(), subTasks, new ItemCallBack() {
            @Override
            public void onSelect(int position) {
                subTasks.remove(position);
            }
        });
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




    @Override
    public void onStop() {
        super.onStop();
        callBackSubTasks.onResult(subTasks);
    }
}
