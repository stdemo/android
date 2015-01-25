package com.stexample.demo.itodo;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.stexample.demo.itodo.datacenter.ToDoData;
import com.stexample.demo.itodo.datacenter.ToDoElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 1/21/15.
 */
public class ViewCollection {

    private Activity mActivity;
    List<String> groupArray = ToDoData.groupArray;
    ArrayList<List<ToDoElement>> childArray = ToDoData.childArray;

    public ViewCollection(Activity activity) {
        ToDoData.initToDoData();

        mActivity = activity;

        //ReminderHandling();
        //DeleteHandling();
        //PriorityHandling();
    }

    private void PriorityHandling() {
        ((Spinner)mActivity.findViewById(R.id.spPriority)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void DeleteHandling() {
        mActivity.findViewById(R.id.ibDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void ReminderHandling() {
        mActivity.findViewById(R.id.ibReminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
