package com.deepakbaliga.getdone.model;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by deepakbaliga on 22/02/17.
 */

public class Task extends RealmObject {


    private long id = 0l;

    private String task= new String();

    private Category category = new Category();

    private boolean timeSet =  false;
    private Date date = new Date();


    private boolean reminderSet = false;


    private boolean subTaskSet = false;
    private RealmList<SubTask> subTasks = new RealmList<>();


    private boolean attachmentSet = false;
    private RealmList<RealmUri> images =  new RealmList<>();

    private boolean audioSet = false;
    private String audioUri = new String();

    private boolean completed = false;


    private Date dateCreated;




    public Task(long id, String task, Category category, boolean timeSet, Date date, boolean reminderSet, boolean subTaskSet, RealmList<SubTask> subTasks, boolean attachmentSet, RealmList<RealmUri> images, boolean audioSet, String audioUri, Date dateCreated) {
        this.id = id;
        this.task = task;
        this.category = category;
        this.timeSet = timeSet;
        this.date = date;
        this.reminderSet = reminderSet;
        this.subTaskSet = subTaskSet;
        this.subTasks = subTasks;
        this.attachmentSet = attachmentSet;
        this.images = images;
        this.audioSet = audioSet;
        this.audioUri = audioUri;
        this.dateCreated = dateCreated;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isTimeSet() {
        return timeSet;
    }

    public Date getDate() {
        return date;
    }

    public boolean isReminderSet() {
        return reminderSet;
    }

    public boolean isSubTaskSet() {
        return subTaskSet;
    }

    public boolean isAttachmentSet() {
        return attachmentSet;
    }



    public boolean isAudioSet() {
        return audioSet;
    }

    public String getAudioUri() {
        return audioUri;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTimeSet(boolean timeSet) {
        this.timeSet = timeSet;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReminderSet(boolean reminderSet) {
        this.reminderSet = reminderSet;
    }

    public void setSubTaskSet(boolean subTaskSet) {
        this.subTaskSet = subTaskSet;
    }



    public void setAttachmentSet(boolean attachmentSet) {
        this.attachmentSet = attachmentSet;
    }



    public void setAudioSet(boolean audioSet) {
        this.audioSet = audioSet;
    }

    public void setAudioUri(String audioUri) {
        this.audioUri = audioUri;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public RealmList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(RealmList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public RealmList<RealmUri> getImages() {
        return images;
    }

    public void setImages(RealmList<RealmUri> images) {
        this.images = images;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public String getDateReadable(){

        if(DateUtils.isToday(date.getTime())){
            return "Today";
        }else {


            String pattern = "dd MMM EEEE";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

            return dateFormat.format(date);
        }


    }
}
