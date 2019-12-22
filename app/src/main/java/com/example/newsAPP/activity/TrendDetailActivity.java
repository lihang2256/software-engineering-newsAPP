package com.example.newsAPP.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.adapter.TCListAdapter;
import com.example.newsAPP.bean.TrendCommentBean;
import com.example.newsAPP.common.DefineView;

import java.util.ArrayList;
import java.util.List;

public class TrendDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = TrendDetailActivity.class.getSimpleName();
    private String trendID;
    private String authorID;
    private String a_name;
    private String t_time;
    private String t_content;
    private String t_newID;
    private String t_newsTitle;
    private String t_newsUrl;
    private String userID;
    private Context mContext;
    private String text;
    private TextView author;
    private TextView time;
    private TextView content;
    private TextView news;
    private ListView listView;
    private ArrayList<TrendCommentBean> beans;
    private TCListAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_detail);
        mContext = this;
        userID = SharedPreferenceUtils.getInstance().getString(mContext,"USERID",null);
        Intent intent = getIntent();
        trendID = intent.getStringExtra("TRENDID");
        authorID = intent.getStringExtra("TAUTHORID");
        a_name = intent.getStringExtra("TNAME");
        t_time = intent.getStringExtra("TTIME");
        t_content = intent.getStringExtra("TCONTENT");
        t_newID = intent.getStringExtra("TNEWSID");
        t_newsTitle = intent.getStringExtra("TNEWSTITLE");
        t_newsUrl = intent.getStringExtra("TNEWSURL");
        author = findViewById(R.id.trend_details_author);
        time = findViewById(R.id.trend_details_time);
        content = findViewById(R.id.trend_details_content);
        news = findViewById(R.id.trend_details_news);
        listView = findViewById(R.id.trend_details_more);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        initToolbar();
        author.setText(a_name);
        time.setText(t_time);
        content.setText(t_content);
        news.setText(t_newsTitle);
    }

    @Override
    public void initValidata() {
        new TrendCommentAsyncTask().execute(trendID);
    }

    @Override
    public void initListener() {
        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TrendDetailActivity.this);
                builder.setTitle("关注");
                builder.setMessage(a_name);
                builder.setPositiveButton("添加好友", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        TODO::添加关注
                        Toast.makeText(TrendDetailActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消添加", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
                builder.show();
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrendDetailActivity.this,NewsDetailActivity.class);
                intent.putExtra("NEWSID",t_newID);
                intent.putExtra("URL",t_newsUrl);
                startActivity(intent);
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trend_pop_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.trend_menu_1:
                AlertDialog.Builder builder = new AlertDialog.Builder(TrendDetailActivity.this);
                builder.setTitle("请输入评论（不超过100字）");
                View view = LayoutInflater.from(TrendDetailActivity.this).inflate(R.layout.dialog, null);
                builder.setView(view);
                final EditText editText = (EditText)view.findViewById(R.id.comment_commit_content);
                text = editText.getText().toString();
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        new IsFriendAsyncTask().execute(userID,authorID);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    { }
                });
                builder.show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    class TrendCommentAsyncTask extends AsyncTask<String,Integer,ArrayList<TrendCommentBean.CommentListBean>>{

        @Override
        protected ArrayList<TrendCommentBean.CommentListBean> doInBackground(String... strings) {
            return null;
        }
    }
    class CommentAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = new HttpUtils().releaseTrend(strings[0],strings[1],strings[2]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(TrendDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
        }
    }
    class IsFriendAsyncTask extends AsyncTask<String,Integer,Boolean>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return new HttpUtils().isFollow(strings[0],strings[1]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                new CommentAsyncTask().execute(userID, text, trendID);
            }
            else {
                Toast.makeText(TrendDetailActivity.this,"请添加关注后评论",Toast.LENGTH_SHORT).show();
            }
        }
    }
    class followAsyncTask extends AsyncTask<String,Integer,Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean result = new HttpUtils().follow(strings[0],strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (!aBoolean) {
                Toast.makeText(TrendDetailActivity.this, "关注失败", Toast.LENGTH_SHORT).show();
            } else {
                isFollowAsyncTask.execute();
            }
        }
    }
    class isFollowAsyncTask extends AsyncTask<String,Integer,Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean result = new HttpUtils().isFollow(strings[0],strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (!aBoolean) {
                //未关注

            } else {
                //已关注
                
            }
        }
    }

}
