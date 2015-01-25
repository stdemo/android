package com.stexample.demo.itodo;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.stexample.demo.itodo.datacenter.ToDoData;
import com.stexample.demo.itodo.datacenter.ToDoElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by steve on 1/24/15.
 */
public class ToDoListener implements
        View.OnClickListener,
        TextWatcher,
        CompoundButton.OnCheckedChangeListener {

    private static final String ST_TAG = "ST_DEUBG";
    private static final int MAX_TITLE_LEN = 40;
    private final ToDoData todoData;
    private String textString = "";
    private List<String> groupArray = ToDoData.groupArray;
    private ArrayList<List<ToDoElement>> childArray = ToDoData.childArray;
    private ToDoElement mElem = null;
    private ToDoExpandableAdapter mAdapter = null;

    // View

    public ToDoListener() {
        todoData = ToDoData.initToDoData();
    }

    @Override
    public void onClick(View v) {

        Log.d("ST_DEBUG", "View ID: " + v.getId());
        switch (v.getId()) {
            /* Image Button Clicked */
            case R.id.ibNew:{
                Log.d(ST_TAG, "ibNew action, textString: " + textString);

                Random r = new Random((new Date()).getTime());

                Log.d(ST_TAG, " " + (new Date()).getTime());

                ToDoElement todo = new ToDoElement(true, false);

                todo.setTitle(textString
                        .substring(0,
                                textString.length() > MAX_TITLE_LEN
                                        ? MAX_TITLE_LEN : textString.length() - 1 ));
                todo.setDetail(textString);

                todoData.addToDoItem(todo);

                break;
            }
            case R.id.ibReact:{
                try {
                    mElem.setDone(false);
                    mAdapter.notifyDataSetChanged();

                } catch (Exception e)
                {
                    Log.d(ST_TAG, "ibReact Error!");
                }
                break;
            }
            case R.id.ibDelete:{
                try {

                    for (int i = 0; i < childArray.size(); i++) {
                        if (childArray.get(i).contains(mElem)) {
                            childArray.get(i).remove(mElem);

                            if ( childArray.get(i).size() == 0 ) {
                                childArray.remove(i);

                                SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-DD");
                                String groupString = dft.format(mElem.getCreateTime());

                                if (groupArray.contains(groupString)) {
                                    groupArray.remove(groupString);
                                }
                            }
                            break;
                        }
                    }

                    mAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.d(ST_TAG, "Delete exception!");
                }

                break;
            }
            case R.id.ibReminder:{
                break;
            }

            /* TextView Clicked */
            case R.id.tvTitle: {
                break;
            }
            case R.id.tvGroupTitle: {
                break;
            }

            default:
                break;
        }
    }

    /* Text Watcher */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Dummy Func.
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textString = s.toString();
        Log.d(ST_TAG, "textString: " + textString);
    }

    @Override
    public void afterTextChanged(Editable s) {
        //Dummy Func.
    }

    public String getTextString() {
        return textString;
    }

    public void setAdapter(ToDoExpandableAdapter adapter, ToDoElement element) {
        mAdapter = adapter;
        mElem = element;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if ( isChecked ) {
            mElem.setDone(true);
            buttonView.setClickable( false );
        }
    }
    /*  */
}
