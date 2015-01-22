package com.stexample.demo.itodo;

import android.text.format.Time;
import android.util.Log;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by steve on 15-1-20.
 */
public class ToDoListItem {

    public static List<String> groupArray;
    public static ArrayList<List<ToDoElement>> childArray;



    public ToDoListItem(int groupCount, int childCount) {

        groupArray = getGroupArray(groupCount);
        childArray = getChildArray(groupCount, childCount);

    }

    private List<String> getGroupArray(int groupCount) {

        groupArray = new ArrayList<String>();

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

        for( int i = 0; i < groupCount; i++ ) {
            Date now = new Date();
            String dateStr = dft.format(now) + ' ' + i;

            groupArray.add("Group " + i);
        }

        return groupArray;
    }

    private ArrayList<List<ToDoElement>> getChildArray(int groupCount, int maxChildCount) {
        //TODO: rewrite


        // Dummy block
        childArray = new ArrayList<List<ToDoElement>>();

        Random r = new Random(10000);

        for (int i = 0; i < groupCount; i++) {
            int childCount = r.nextInt(maxChildCount);
            List<ToDoElement> childElem = new ArrayList<ToDoElement>();

            for (int j = 0; j < childCount; j++) {
                ToDoElement elem = new ToDoElement(true);
                elem.setTitle("Elem " + j + " in Group " + i);
                elem.setDetail("show something detailed within this view...");
                elem.setPriority(r.nextInt(3));
                elem.setDone( r.nextBoolean());
                elem.setReminder(r.nextBoolean());
                childElem.add(elem);
            }

            childArray.add(childElem);
        }
        ///~ Dummy done
        return childArray;
    }


    static class ToDoElement {
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

        public ToDoElement(boolean created) {
            if (created) {
                GenerateId();
                ToDoCreateTime();
            }
        }

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



}



