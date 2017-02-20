package com.deepakbaliga.getdone.callback;

import com.deepakbaliga.getdone.model.SubTask;

import java.util.LinkedList;

/**
 * Created by deepakbaliga on 20/02/17.
 */

public interface CallBackSubTasks {

    public void onResult(LinkedList<SubTask> subTaskList);
}
