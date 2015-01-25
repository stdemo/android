package com.stexample.demo.itodo.datacenter;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by steve on 1/25/15.
 */
public class ToDoData {

    private static final int DUMMY_COUNT = 20; //DUMMY_DATA
    private static final SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-DD");
    private static int groupIndex = 0;
    private static ToDoData mData = null;

    public static Map<String, List<ToDoElement>> dataMap;
    public static List<String> groupArray;
    public static ArrayList<List<ToDoElement>> childArray;
    private List<ToDoElement> mTodoItemArray;

    private ToDoData() {
        groupArray = new ArrayList<String>();
        childArray = new ArrayList<List<ToDoElement>>();

        getTodoItemArray();

        for (int i = 0; i < mTodoItemArray.size(); i++) {
            groupTodoItem(mTodoItemArray.get(i));
        }
    }

    public static ToDoData initToDoData() {
        if (mData == null) {
            mData = new ToDoData();
        }
        return mData;
    }

    public List<ToDoElement> getTodoItemArray() {

        mTodoItemArray = new ArrayList<ToDoElement>();

        int elementCount = DUMMY_COUNT;

        for (int i = 0; i < elementCount; i++) {

            ToDoElement element;
            //: DUMMY Data Generator
            element = DummyDataGenerator();
            ///~
            //: Get Element Data ///~

            mTodoItemArray.add(element);
        }

        return mTodoItemArray;
    }

    private void groupTodoItem(ToDoElement elem) {

        long createTime = elem.getCreateTime().getTime();
        String groupString = dft.format(createTime);

        if (!groupArray.contains(groupString)) {
            groupArray.add(groupString);

            ArrayList<ToDoElement> groupElements = new ArrayList<ToDoElement>();
            groupElements.add(elem);
            childArray.add(groupIndex, groupElements);
            groupIndex++;

            Log.d("STC", "Group String: " + groupString);
            Log.d("STC", "Child Element: " + elem.getCreateTime().toString());

        } else {
            int idx = groupArray.indexOf(groupString);
            ArrayList<ToDoElement> groupElements = (ArrayList<ToDoElement>)childArray.get(idx);
            groupElements.add(elem);

            Log.d("STC", "Group String: " + groupString);
            Log.d("STC", "++> Child Element: " + elem.getCreateTime().toString());
        }
    }

    public void addToDoItem(ToDoElement elem) {
        //: add something to database ...
        // TODO: add something more.
        ///~
        groupTodoItem(elem);
    }

    //: DUMMY Data
    private ToDoElement DummyDataGenerator() {
        ToDoElement elem = new ToDoElement(true, true);
        Random r = new Random(new Date().getTime());

        elem.setTitle("Dummy: " + new SimpleDateFormat("MM-DD HH:MM:SS").format(elem.getCreateTime()) );
        elem.setDetail("show something detailed within this view...");
        elem.setPriority(r.nextInt(3));
        elem.setDone( r.nextBoolean());
        elem.setReminder(r.nextBoolean());

        return elem;
    } ///~

}
