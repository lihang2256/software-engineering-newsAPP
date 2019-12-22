package com.example.newsAPP.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsAPP.R;
import com.example.newsAPP.common.DefineView;

public class TrendDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private int mID;
    private int trendID;
    private Context mContext;
    private SharedPreferences sharedPreferences;
    //private ArrayList<CommentListBean> commentlistBeans;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_detail);
        mContext = this;
        Intent intent = getIntent();
        trendID = intent.getIntExtra("TID",-1);
        //mID = intent.getIntExtra("ID",-1);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        initToolbar();
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("动态详情");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
