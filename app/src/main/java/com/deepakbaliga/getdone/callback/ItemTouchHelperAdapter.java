package com.deepakbaliga.getdone.callback;

/**
 * Created by deepakbaliga on 20/02/17.
 */

public interface ItemTouchHelperAdapter {


    void onItemMove(int fromPosition, int toPosition);



    void onItemDismiss(int position);
}
