package com.stexample.demo.itodo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity
{
    static final String ST_TAG = "ST_DEBUG";

    public static List<String> groupList = null;
    public static ArrayList<List<ToDoListItem.ToDoElement>> childList = null;

    private ExpandableListView expListView = null;

    private String newTodoString = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ToDoListItem todoListItem = new ToDoListItem(5, 18);

        groupList = ToDoListItem.groupArray;
        childList = ToDoListItem.childArray;

        ToDoExpandableAdapter adapter = new ToDoExpandableAdapter(this);

        expListView = (ExpandableListView) findViewById(R.id.expListView);
        expListView.setAdapter(adapter);

        NewToDoEditListener();

        new DetailedView(this);
    }

    public void clickHandle(View source) {

        Random r = new Random((new Date()).getTime());

        Log.d(ST_TAG, " " + (new Date()).getTime());

        int group = r.nextInt(groupList.size());

        ToDoListItem.ToDoElement todo = new ToDoListItem.ToDoElement(true);
        todo.setTitle(newTodoString);
        todo.setDetail(newTodoString);
        Log.d(ST_TAG, todo.getCreateTime().toString());
        childList.get(group).add(todo);
        /*
        findViewById(R.id.ibNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //:TODO: Debug block

                Random r = new Random((new Date()).getTime());

                Log.d(ST_TAG, " " + (new Date()).getTime());

                int group = r.nextInt(groupList.size());

                ToDoListItem.ToDoElement todo = new ToDoListItem.ToDoElement(true);
                todo.setTitle(newTodoString);
                todo.setDetail(newTodoString);
                Log.d(ST_TAG, todo.getCreateTime().toString());
                childList.get(group).add(todo);
                ///~
            }
        }); */
    }

    private void NewToDoEditListener() {
        ((EditText)findViewById(R.id.etNew)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newTodoString = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    public class ToDoExpandableAdapter extends BaseExpandableListAdapter {

        private Activity mActivity;

        public ToDoExpandableAdapter(Activity activity) { mActivity = activity; }

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.group_title, parent, false);
            }

            String str = groupList.get(groupPosition);

            ((TextView)convertView.findViewById(R.id.tvGroupTitle)).setText(str);
            Log.d(ST_TAG, "********** group ********* " + groupPosition);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.todo_child_item, parent, false);
            }

            final View finalCovertView = convertView;

            ToDoListItem.ToDoElement elem = (ToDoListItem.ToDoElement)childList.get(groupPosition).get(childPosition);

            Log.d(ST_TAG, "********** child ********* " + childPosition);
            Log.d(ST_TAG, elem.getDetail());
            Log.d(ST_TAG, elem.getTitle());

            ((TextView)convertView.findViewById(R.id.tvTitle)).setText(elem.getTitle());
            ((TextView)convertView.findViewById(R.id.etDetail)).setText(elem.getDetail());
            ((CheckBox)convertView.findViewById(R.id.cbDone)).setChecked(elem.getDone());
            ((Spinner)convertView.findViewById(R.id.spPriority)).setSelection(elem.getPriority());
            convertView.findViewById(R.id.ivIndReminder).setVisibility(
                    elem.getReminder() == true ? View.VISIBLE : View.GONE
            );

            convertView.findViewById(R.id.tvTitle).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View layoutDetail = finalCovertView.findViewById(R.id.llDetails);
                            layoutDetail.setVisibility(
                                    layoutDetail.getVisibility() == View.VISIBLE ?
                                            View.GONE : View.VISIBLE);
                            notifyDataSetChanged();
                        }
                    }
            );

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


}
