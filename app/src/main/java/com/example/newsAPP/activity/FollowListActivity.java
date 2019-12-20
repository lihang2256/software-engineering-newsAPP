package com.example.newsAPP.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsAPP.R;
import com.example.newsAPP.adapter.FollowListAdapter.ContentsDeleteListener;
import com.example.newsAPP.adapter.FollowListAdapter;
import com.example.newsAPP.common.DefineView;

public class FollowListActivity extends BaseActivity implements ContentsDeleteListener,OnClickListener, DefineView {
    private  ListView myLv;
    private Button myDeleteBtn;
    private FollowListAdapter myAdapter;
    private String[] myContentsArray;
    private List<String> myContentsList = new ArrayList<String>();
    private List<String> mySelectedList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_list);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        myLv = (ListView) this.findViewById(R.id.my_lv1);
        myDeleteBtn = (Button) this.findViewById(R.id.my_delete_btn1);
        myDeleteBtn.setOnClickListener(this);
        initToolbar();
    }

    @Override
    public void initValidata() {
        bindData();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        myContentsArray = this.getResources().getStringArray(R.array.my_follow);
        if(myContentsArray != null){
            Collections.addAll(myContentsList, myContentsArray);
        }
        myAdapter = new FollowListAdapter(this,myContentsList,this);
        myLv.setAdapter(myAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * 根据isChecked,给选择的List中添加或删除数据
     * (non-Javadoc)
     * @see com.example.slideandselectdeletedemo.MyAdapter.ContentsDeleteListener#contentsDeleteSelect(int, boolean)
     */
    @Override
    public void contentsDeleteSelect(int position,boolean isChecked) {
        if(isChecked){
            mySelectedList.add(myContentsList.get(position));
        }else{
            mySelectedList.remove(myContentsList.get(position));
        }
    }

    /*
     * 删除指定位置的数据
     * (non-Javadoc)
     * @see com.example.slideandselectdeletedemo.MyAdapter.ContentsDeleteListener#contentDelete(int)
     */
    @Override
    public void contentDelete(int position) {
        // TODO Auto-generated method stub
        myContentsList.remove(position);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.my_delete_btn1:
                myContentsList.removeAll(mySelectedList);
                myAdapter.updateView(myContentsList);
                break;
        }
    }
    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("关注");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }
}


