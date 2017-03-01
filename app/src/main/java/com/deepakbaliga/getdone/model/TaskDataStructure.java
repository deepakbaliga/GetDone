package com.deepakbaliga.getdone.model;

/**
 * Created by deepakbaliga on 01/03/17.
 */

public class TaskDataStructure {

    private int type;
    private Object data;

    public TaskDataStructure() {
    }

    public TaskDataStructure(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
