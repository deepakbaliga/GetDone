package com.deepakbaliga.getdone.model;

import java.io.Serializable;

/**
 * Created by deepakbaliga on 14/02/17.
 */

public class Category implements Serializable {

    private String categoryTitle;
    private int remainingTasks;
    private int icon;
    private int color;

    public Category() {
    }

    public Category(String categoryTitle, int remainingTasks, int icon, int color) {
        this.categoryTitle = categoryTitle;
        this.remainingTasks = remainingTasks;
        this.icon = icon;
        this.color = color;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getRemainingTasks() {
        return remainingTasks;
    }

    public void setRemainingTasks(int remainingTasks) {
        this.remainingTasks = remainingTasks;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
