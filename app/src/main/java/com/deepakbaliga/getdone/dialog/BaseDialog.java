package com.deepakbaliga.getdone.dialog;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.deepakbaliga.getdone.widget.WheelMinutePicker;

import java.util.Date;



public abstract class BaseDialog {

    public static final int DEFAULT_ITEM_COUNT_MODE_CURVED = 7;
    public static final int DEFAULT_ITEM_COUNT_MODE_NORMAL = 5;

    @Nullable
    private boolean isDisplaying;
    @ColorInt
    protected Integer backgroundColor = null;

    @Nullable
    @ColorInt
    protected Integer mainColor = null;

    @Nullable
    @ColorInt
    protected Integer titleTextColor = null;

    protected boolean okClicked = false;
    protected boolean curved = false;
    protected boolean mustBeOnFuture = false;
    protected int minutesStep = WheelMinutePicker.STEP_MINUTES_DEFAULT;

    @Nullable
    protected Date minDate;
    @Nullable
    protected Date maxDate;
    @Nullable
    protected Date defaultDate;


    public void display() {
        this.isDisplaying = true;
    }

    public void close() {
        this.isDisplaying = false;
    }

    public boolean isDisplaying() {
        return isDisplaying;
    }

    public void setBackgroundColor(@ColorInt Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setMainColor(@ColorInt Integer mainColor) {
        this.mainColor = mainColor;
    }

    public void setTitleTextColor(@NonNull @ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    protected void onClose() {
        this.isDisplaying = false;
    }
}
