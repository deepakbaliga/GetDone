package com.deepakbaliga.getdone.model;

/**
 * Created by deepakbaliga on 20/02/17.
 */

public class SubTask {

    private long id;
    private String subTask;



    public SubTask(long id, String subTask) {
        this.id = id;
        this.subTask = subTask;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
    }
}
