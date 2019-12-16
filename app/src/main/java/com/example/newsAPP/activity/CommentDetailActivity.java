package com.example.newsAPP.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.newsAPP.R;
import com.example.newsAPP.common.DefineView;

public class CommentDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private int mID;
    private int commentID;
    private Context mContext;
    private SharedPreferences sharedPreferences;
    //private ArrayList<CommentListBean> commentlistBeans;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        mContext = this;
        Intent intent = getIntent();
        commentID = intent.getIntExtra("CID",-1);
        //mID = intent.getIntExtra("ID",-1);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {

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
}
