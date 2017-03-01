package com.deepakbaliga.getdone.model;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmList;
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


    public static RealmList<SubTask> getRealList(Realm realm, LinkedList<SubTask> subTasks){

        RealmList<SubTask> mRealmList = new RealmList<>();

        for(int i=0; i<subTasks.size(); i++){

            SubTask subTask = realm.createObject(SubTask.class);
            subTask.setId(subTasks.get(i).getId());
            subTask.setSubTask(subTasks.get(i).getSubTask());
            mRealmList.add(subTask);

        }

        return mRealmList;



    }
}
