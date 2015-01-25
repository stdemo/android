package com.stexample.demo.itodo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.stexample.demo.itodo.datacenter.ToDoData;
import com.stexample.demo.itodo.datacenter.ToDoElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 1/25/15.
 */
public class ToDoExpandableAdapter extends BaseExpandableListAdapter {

    private Activity mActivity;
    private List<String> mGroupList;
    private ArrayList<List<ToDoElement>> mChildList;

    public ToDoExpandableAdapter(Activity activity) {
        ToDoData.initToDoData();

        mActivity = activity;
        mGroupList = ToDoData.groupArray;
        mChildList = ToDoData.childArray;
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
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
            convertView = mActivity.getLayoutInflater().inflate(R.layout.group_title, parent, false);
        }

        // Display group name
        String str = mGroupList.get(groupPosition);
        ((TextView)convertView.findViewById(R.id.tvGroupTitle)).setText(str);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.todo_child_item, parent, false);
        }

        // Display Child Info
        final View finalCovertView = convertView;
        final ToDoElement elem = (ToDoElement)mChildList.get(groupPosition).get(childPosition);
        final ToDoListener todoListener = new ToDoListener();
        todoListener.setAdapter(this, elem);

        ((TextView)convertView.findViewById(R.id.tvTitle)).setText(elem.getTitle());
        ((TextView)convertView.findViewById(R.id.etDetail)).setText(elem.getDetail());

        CheckBox cb = (CheckBox)convertView.findViewById(R.id.cbDone);

        cb.setChecked(elem.getDone());
        if (!elem.getDone()) {
            cb.setClickable(true);
        }
        cb.setOnCheckedChangeListener(todoListener);

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

                        if ( layoutDetail.getVisibility() == View.VISIBLE ) {


                            ((TextView)finalCovertView.findViewById(R.id.etDetail))
                                    .addTextChangedListener(todoListener);
                            ((ImageButton)finalCovertView.findViewById(R.id.ibDelete))
                                    .setOnClickListener(todoListener);
                            ((ImageButton)finalCovertView.findViewById(R.id.ibReminder))
                                    .setOnClickListener(todoListener);
                            ((ImageButton)finalCovertView.findViewById(R.id.ibReact))
                                    .setOnClickListener(todoListener);
                        }

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

