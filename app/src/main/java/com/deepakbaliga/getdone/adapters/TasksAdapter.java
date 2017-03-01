package com.deepakbaliga.getdone.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.customViews.BoldTextView;
import com.deepakbaliga.getdone.customViews.RegularTextView;
import com.deepakbaliga.getdone.model.Task;
import com.deepakbaliga.getdone.model.TaskDataStructure;
import com.deepakbaliga.getdone.utilities.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakbaliga on 01/03/17.
 */

public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    ArrayList<TaskDataStructure> list =  new ArrayList<>();

    public TasksAdapter(Context context, ArrayList<TaskDataStructure> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if(viewType == Constants.ITEM_DAY){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_indicator, parent, false);

            return new DayViewHolder(view);


        }else if(viewType == Constants.ITEM_CARD){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_card, parent, false);
            return new TaskCardViewHolder(view);


        }else{
            return null;
        }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int type = list.get(position).getType();


        if(type == Constants.ITEM_DAY){
            DayViewHolder dayViewHolder = (DayViewHolder) holder;
            String day = (String) list.get(position).getData();

            dayViewHolder.dayIndicator.setText(day);




        }else{
            final TaskCardViewHolder taskCardViewHolder = (TaskCardViewHolder) holder;
            final Task task = (Task) list.get(position).getData();

            taskCardViewHolder.taskTitle.setText(task.getTask());

            /////////////// Category Color ///////////////
            taskCardViewHolder.categoryColor.setBackgroundColor(context.getResources().getColor(task.getCategory().getColor()));



            /////////////// Time Setting ///////////////
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            if (task.isTimeSet()){

                taskCardViewHolder.taskTime.setText(dateFormat.format(task.getDate()));
                taskCardViewHolder.taskTime.setVisibility(View.VISIBLE);


            }else{
                taskCardViewHolder.taskTime.setText("");
                taskCardViewHolder.taskTime.setVisibility(View.GONE);

            }



            /////////////// Reminder ///////////////

            if(task.isReminderSet()){
                taskCardViewHolder.reminderIcon.setVisibility(View.VISIBLE);
            }else{
                taskCardViewHolder.reminderIcon.setVisibility(View.GONE);
            }

            /////////////// Comments ///////////////

            if(task.getSubTasks().size()>0){
                taskCardViewHolder.commentIcon.setVisibility(View.VISIBLE);
            }else{
                taskCardViewHolder.commentIcon.setVisibility(View.GONE);
            }



            /////////////// Task Completed ///////////////

            if(task.isCompleted()){


                taskCardViewHolder.checked.setChecked(true);
                taskCardViewHolder.taskTitle.setTextColor(context.getResources().getColor(R.color.light_grey));


            }else {
                taskCardViewHolder.checked.setChecked(false);
                taskCardViewHolder.taskTitle.setTextColor(context.getResources().getColor(R.color.dark_grey));
            }

            taskCardViewHolder.checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b){

                         StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();

                        taskCardViewHolder.taskTitle.setText(task.getTask(), TextView.BufferType.SPANNABLE);
                        Spannable spannable = (Spannable) taskCardViewHolder.taskTitle.getText();

                        spannable.setSpan(STRIKE_THROUGH_SPAN, 0, task.getTask().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        taskCardViewHolder.checked.setChecked(true);
                        taskCardViewHolder.taskTitle.setTextColor(context.getResources().getColor(R.color.light_grey));
                        taskCardViewHolder.taskTime.setTextColor(context.getResources().getColor(R.color.light_grey));


                    }else {
                        taskCardViewHolder.checked.setChecked(false);
                        taskCardViewHolder.taskTitle.setTextColor(context.getResources().getColor(R.color.dark_grey));
                        taskCardViewHolder.taskTime.setTextColor(context.getResources().getColor(R.color.dark_grey));
                        taskCardViewHolder.taskTitle.setText(task.getTask());

                    }


                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {

        return list.get(position).getType();
    }

    public class TaskCardViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.task_checked)
        AppCompatCheckBox checked;

        @BindView(R.id.task_title)
        RegularTextView taskTitle;

        @BindView(R.id.time_textview)
        BoldTextView taskTime;

        @BindView(R.id.comment_icon)
        ImageView commentIcon;

        @BindView(R.id.reminder_icon)
        ImageView reminderIcon;

        @BindView(R.id.category_color)
        LinearLayout categoryColor;


        public TaskCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class DayViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.day_indicator)
        BoldTextView dayIndicator;


        public DayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
