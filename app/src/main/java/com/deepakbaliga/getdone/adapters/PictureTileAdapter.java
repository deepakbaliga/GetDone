package com.deepakbaliga.getdone.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.callback.ItemCallBack;
import com.deepakbaliga.getdone.customViews.SquareImageView;
import com.deepakbaliga.getdone.utilities.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakbaliga on 17/02/17.
 */

public class PictureTileAdapter extends RecyclerView.Adapter<PictureTileAdapter.TileViewHolder> {


    private Context context;
    private ArrayList<Uri> uriList = new ArrayList<>();
    private ItemCallBack itemCallBack;
    private int lastPosition = -1;

    public PictureTileAdapter(Context context, ArrayList<Uri> uriList, ItemCallBack itemCallBack) {
        this.context = context;
        this.uriList = uriList;
        this.itemCallBack = itemCallBack;
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_square_picture, parent, false);
        TileViewHolder viewHolder = new TileViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TileViewHolder holder, final int position) {


        Glide.with(context)
                .load(uriList.get(position))
                .bitmapTransform(new RoundedCornersTransformation(context,30,0))
                .into(holder.tile);

        setAnimation(holder.itemView, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteWithAnimation(holder.itemView, position);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteWithAnimation(holder.itemView, position);
            }
        });



    }

    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.rise_from_bottom_little_later);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void deleteWithAnimation(View viewToAnimate, final int position)
    {


            Animation animation = AnimationUtils.loadAnimation(context, R.anim.delete_picture);
            viewToAnimate.startAnimation(animation);
            itemCallBack.onSelect(position);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {


                    notifyDataSetChanged();


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public void setUriList(ArrayList<Uri> uriList) {
        this.uriList = uriList;
    }

    public class TileViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_tile)
        SquareImageView tile;

        @BindView(R.id.tile_delete)
        ImageButton deleteButton;

        public TileViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
