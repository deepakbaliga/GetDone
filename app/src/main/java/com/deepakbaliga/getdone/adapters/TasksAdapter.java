package com.deepakbaliga.getdone.adapters;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.customViews.BoldTextView;
import com.deepakbaliga.getdone.customViews.RegularTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakbaliga on 01/03/17.
 */

public class TasksAdapter {



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
        View categoryColor;


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
