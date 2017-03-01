package com.deepakbaliga.getdone.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.callback.ItemCallBack;
import com.deepakbaliga.getdone.customViews.CircularImageView;
import com.deepakbaliga.getdone.customViews.RegularTextView;
import com.deepakbaliga.getdone.model.Category;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakbaliga on 15/02/17.
 */

public class CategoriesAddTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int selected = -1;
    private LinkedList<Category> categories = new LinkedList<>();

    private final int SELECTED_LAYOUT = 1;
    private final int UNSELECTED_LAYOUT = 0;

    private ItemCallBack itemCallBack;

    public CategoriesAddTaskAdapter(Context context, int selected, LinkedList<Category> categories, ItemCallBack itemCallBack) {
        this.context = context;
        this.selected = selected;
        this.categories = categories;
        this.itemCallBack = itemCallBack;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType==UNSELECTED_LAYOUT){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_in_add_task, parent,false);
            CategoryViewHolder holder = new CategoryViewHolder(view);
            return holder;
        }else{

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_in_add_task_selected, parent,false);
            CategoryViewHolderSelected holder = new CategoryViewHolderSelected(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Category category = categories.get(position);


        if(position==selected){

            CategoryViewHolderSelected holderSelected = (CategoryViewHolderSelected) holder;

            holderSelected.categoryTitle.setText(category.getCategoryTitle());
            holderSelected.categoryTitle.setTextColor(context.getResources().getColor(android.R.color.white));
            RippleDrawable drawable = (RippleDrawable) context.getDrawable(R.drawable.ripple_rectangle_slight_corners);
            holderSelected.itemView.setBackground(drawable);
            holderSelected.itemView.setClickable(true);




        }else{

            CategoryViewHolder holderUnselected = (CategoryViewHolder) holder;
            holderUnselected.categoryColor.setColorFilter(context.getResources().getColor(category.getColor()));
            holderUnselected.categoryTitle.setText(category.getCategoryTitle());
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCallBack.onSelect(position);
                selected = position;



            }
        });

    }

    @Override
    public int getItemViewType(int position) {

        if(position==selected)
            return SELECTED_LAYOUT;
        else
            return UNSELECTED_LAYOUT;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.category_color)
        CircularImageView categoryColor;

        @BindView(R.id.category_title)
        RegularTextView categoryTitle;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public class CategoryViewHolderSelected extends RecyclerView.ViewHolder{

        @BindView(R.id.category_title)
        RegularTextView categoryTitle;

        public CategoryViewHolderSelected(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);



        }
    }


}
