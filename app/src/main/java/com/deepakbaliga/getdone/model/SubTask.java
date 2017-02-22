package com.deepakbaliga.getdone.model;

import io.realm.RealmObject;

/**
 * Created by deepakbaliga on 20/02/17.
 */

public class SubTask  extends RealmObject {

    private long id;
    private String subTask;

    public SubTask() {
    }

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
