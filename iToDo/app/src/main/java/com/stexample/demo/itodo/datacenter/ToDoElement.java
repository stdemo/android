package com.stexample.demo.itodo.datacenter;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by steve on 1/25/15.
 */
public class ToDoElement {

    // item elements
    private int mId = 0;
    private String mTitle = null;
    private boolean mDone = false;
    private String mDetail = null;
    private Date mCreateTime = null;
    private boolean mReminder = false;
    private Date mReminderTime = null;
    private int mPriority = 0;

    public ToDoElement() {}

    public ToDoElement(boolean created, boolean isDummyData) {

        if (created) {
            GenerateId();
            if (!isDummyData) {
                ToDoCreateTime();
            } else {
                DummyCreateTime();
            }
        }
    }

    //: DUMMY Data
    private void DummyCreateTime() {
        Random r = new Random(new Date().getTime());
        mCreateTime = new Date();
        mCreateTime.setTime(new Date().getTime() - 3600 * 1000 * r.nextInt(24) * r.nextInt(7));
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("ST_DEBUG", dft.format(mCreateTime));
    } ///~

    // public set/get functions
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setDone(final boolean done) {
        mDone = done;
    }

    public boolean getDone() {
        return mDone;
    }

    public void setDetail(final String detail) {
        mDetail = detail;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setReminder(final boolean reminder){
        mReminder = reminder;
    }

    public boolean getReminder(){
        return mReminder;
    }

    public void setReminderTime(final Date reminderTime) {
        mReminderTime = reminderTime;
    }

    public Date getReminderTime() {
        return mReminderTime;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public int getPriority() {
        return mPriority;
    }

    public Date getCreateTime() {
        return mCreateTime;
    }

    // private functions
    private void GenerateId() {
        //TODO: need re-write this method

        // dummy data
        Random r = new Random(10000);
        mId = r.nextInt(1000);
        ///~ dummy end
    }

    private void ToDoCreateTime() {
        mCreateTime = new Date();
    }
}