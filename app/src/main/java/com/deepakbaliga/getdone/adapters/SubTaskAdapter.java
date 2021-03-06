package com.deepakbaliga.getdone.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.callback.ItemCallBack;
import com.deepakbaliga.getdone.callback.ItemTouchHelperAdapter;
import com.deepakbaliga.getdone.callback.ItemTouchHelperViewHolder;
import com.deepakbaliga.getdone.customViews.ThinTextView;
import com.deepakbaliga.getdone.model.SubTask;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakbaliga on 20/02/17.
 */

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.SubTaskViewHolder> implements ItemTouchHelperAdapter {


    private Context context;
    private LinkedList<SubTask> subTasks = new LinkedList<>();
    private ItemCallBack callBack;

    private int lastPosition = -1;
    public SubTaskAdapter(Context context, LinkedList<SubTask> subTasks, ItemCallBack callBack) {
        this.context = context;
        this.subTasks = subTasks;
        this.callBack = callBack;
    }

    @Override
    public SubTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_task, parent, false);
        SubTaskViewHolder viewHolder = new SubTaskViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubTaskViewHolder holder, int position) {

        SubTask subTask = subTasks.get(position);
        holder.task.setText(subTask.getSubTask());

        setAnimation(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return subTasks.size();
    }



    @Override
    public void onItemDismiss(int position) {

        subTasks.remove(position);
        notifyItemRemoved(position);
        lastPosition--;
    }

    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }



    public class SubTaskViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        @BindView(R.id.sub_task_item_text)
        ThinTextView task;

        public SubTaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
            itemView.setBackground(context.getResources().getDrawable(android.R.drawable.list_selector_background));
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
