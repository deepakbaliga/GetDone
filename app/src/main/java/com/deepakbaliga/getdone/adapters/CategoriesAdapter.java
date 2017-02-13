package com.deepakbaliga.getdone.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.customViews.CircularImageView;
import com.deepakbaliga.getdone.customViews.RegularTextView;
import com.deepakbaliga.getdone.model.Category;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakbaliga on 14/02/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private LinkedList<Category> categories = new LinkedList<>();

    private final int NORMAL_TILE = 1;
    private final int ADD_NEW_CATEGORY = 2;


    public CategoriesAdapter(Context context, LinkedList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == NORMAL_TILE){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_active_project_card, parent,false);
            CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
            return categoryViewHolder;
        }else if(viewType == ADD_NEW_CATEGORY){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_project_card, parent,false);
            AddCategoryViewHolder addCategoryViewHolder = new AddCategoryViewHolder(view);
            return addCategoryViewHolder;

        } else return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position<categories.size()){

            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
            Category category = categories.get(position);

            categoryViewHolder.titleTextView.setText(category.getCategoryTitle());
            categoryViewHolder.taskCountTextView.setText(category.getRemainingTasks()+" Tasks");
            categoryViewHolder.color.setColorFilter(context.getResources().getColor(category.getColor()));
            categoryViewHolder.color.setAlpha(0.15f);
            categoryViewHolder.icon.setImageDrawable(context.getResources().getDrawable(category.getIcon()));
            categoryViewHolder.icon.setColorFilter(context.getResources().getColor(category.getColor()));
            categoryViewHolder.icon.setAlpha(0.5f);
        }else{


            AddCategoryViewHolder categoryViewHolder = (AddCategoryViewHolder) holder;

            categoryViewHolder.color.setColorFilter(context.getResources().getColor(R.color.grey));
            categoryViewHolder.color.setAlpha(0.15f);
            categoryViewHolder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_add_category));
            categoryViewHolder.icon.setColorFilter(context.getResources().getColor(R.color.grey));
            categoryViewHolder.icon.setAlpha(0.5f);

        }



    }

    @Override
    public int getItemCount() {
        return categories.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position<categories.size()){
            return NORMAL_TILE;
        }else{
            return ADD_NEW_CATEGORY;
        }
    }

    public   class CategoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.category_icon)
        CircularImageView icon;
        @BindView(R.id.category_color)
        CircularImageView color;
        @BindView(R.id.category_title)
        RegularTextView titleTextView;
        @BindView(R.id.category_tasks_count)
        RegularTextView taskCountTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


    public class AddCategoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.category_icon)
        CircularImageView icon;
        @BindView(R.id.category_color)
        CircularImageView color;
        @BindView(R.id.category_title)
        RegularTextView titleTextView;

        public AddCategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}
