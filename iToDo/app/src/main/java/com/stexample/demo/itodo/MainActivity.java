package com.stexample.demo.itodo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.stexample.demo.itodo.datacenter.ToDoData;
import com.stexample.demo.itodo.datacenter.ToDoElement;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    static final String ST_TAG = "ST_DEBUG";

    public static List<String> groupList = null;
    public static ArrayList<List<ToDoElement>> childList = null;

    private ExpandableListView expListView = null;

    private String newTodoString = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ToDoListener todoListener = new ToDoListener();

        ToDoData.initToDoData();

        groupList = ToDoData.groupArray;
        childList = ToDoData.childArray;
       // new ViewCollection(this);

        ((ExpandableListView)findViewById(R.id.expListView))
                .setAdapter(new ToDoExpandableAdapter(this));
        ((EditText)findViewById(R.id.etNew)).addTextChangedListener(todoListener);
        ((ImageButton)findViewById(R.id.ibNew)).setOnClickListener(todoListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate the menu
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_search:
                break;
            case R.id.action_settings:
                break;
            default:
                break;
        }

        return true;
    }
}
