package com.example.newsAPP.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.adapter.NTListAdapter;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.bean.TrendCommentBean;
import com.example.newsAPP.common.DefineView;

import java.util.ArrayList;

public class NewsDetailActivity extends BaseActivity implements DefineView{
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private String newsID;
    private Context mContext;
    private WebView mWebView;
    private String mUrl;
    private String userID;
    private ArrayList<CommentBean.DataBean> commentBeans;
    private NTListAdapter adapter;
    private ListView listView;
    private EditText editText;
    private String text;
    private String authorID;
    private String authorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mContext = this;
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("URL");
        userID = SharedPreferenceUtils.getInstance().getString(mContext,"USERID",null);
        newsID = intent.getStringExtra("NEWSID");
        listView = findViewById(R.id.news_trend_list);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_pop_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_1:
                AlertDialog.Builder builder = new AlertDialog.Builder(NewsDetailActivity.this);
                builder.setTitle("请输入评论（不超过100字）");
                View view = LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.dialog, null);
                builder.setView(view);
                editText = (EditText)view.findViewById(R.id.comment_commit_content);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        text = editText.getText().toString();
                        if (text.equals("")){
                            Toast.makeText(NewsDetailActivity.this,"不可以发表空评论",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            new CommentAsyncTask().execute(userID,text, newsID);
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
                builder.show();
                break;
            case R.id.menu_2:
                //判断是否收藏
                new IsCollectedAsyncTask().execute("query",userID,newsID,"two");
                break;
            case R.id.menu_3:
                new IsCollectedAsyncTask().execute("query",userID,newsID,"three");
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void initView() {
        initToolbar();
        mWebView = (WebView) findViewById(R.id.details_content);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("新闻详情");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    /**
     * 去除网页内的广告
     */
    @Override
    public void initValidata() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                view.loadUrl("javascript:function mFunc(){" +
                        "var array = document.querySelectorAll('.top-wrap');" +
                        "for(var i=0;i<array.length;i+=1){" +
                        "array[i].style.display='none';" +
                        "}" +
                        "}" +
                        "mFunc();");
                view.loadUrl("javascript:function mFunc(){" +
                        "var array = document.querySelectorAll('.articledown-wrap');" +
                        "for(var i=0;i<array.length;i+=1){" +
                        "array[i].style.display='none';" +
                        "}" +
                        "}" +
                        "mFunc();");
                view.loadUrl("javascript:function mFunc(){" +
                        "var array = document.querySelectorAll('#news_check');" +
                        "for(var i=0;i<array.length;i+=1){" +
                        "array[i].style.display='none';" +
                        "}" +
                        "}" +
                        "mFunc();");
            }
        });
        mWebView.loadUrl(mUrl);
        new NewsDetailsAsyncTask().execute(newsID);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        if (commentBeans == null||commentBeans.size() <= 0){ }
        else {
            adapter = new NTListAdapter(this,commentBeans);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CommentBean.DataBean bean = commentBeans.get(position);
                    authorID = bean.getUser_id();
                    authorName = bean.getNick_name();
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewsDetailActivity.this);
                    builder.setTitle("关注");
                    builder.setMessage(authorName);
                    builder.setPositiveButton("添加关注", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            new IsFollowedAsyncTask().execute(userID,authorID);
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
        }
    }

    class IsCollectedAsyncTask extends AsyncTask<String,Integer,ArrayList<String>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            ArrayList<String> result = new ArrayList<>();
            result.add(new HttpUtils().collect(strings[0],strings[1],strings[2]));
            result.add(strings[3]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            if (result.get(0).equals("True")&&result.get(1).equals("two")){
                Toast.makeText(NewsDetailActivity.this,"已收藏，不要重复点击",Toast.LENGTH_SHORT).show();
            }
            else if (result.get(0).equals("False")&&result.get(1).equals("two")) {
                new CollectonAsyncTask().execute("add",userID,newsID);
            }
            else if (result.get(0).equals("True")&&result.get(1).equals("three")) {
                new CollectonAsyncTask().execute("delete",userID,newsID);
            }
            else if (result.get(0).equals("False")&&result.get(1).equals("three")) {
                Toast.makeText(NewsDetailActivity.this,"未收藏，不可取消收藏",Toast.LENGTH_SHORT).show();
            }
            else{

            }
        }
    }
    class CollectonAsyncTask extends AsyncTask<String,Integer,String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = new HttpUtils().collect(strings[0],strings[1],strings[2]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("True")) {
                Toast.makeText(NewsDetailActivity.this,"成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(NewsDetailActivity.this,"失败",Toast.LENGTH_SHORT).show();
            }
        }

    }
    class NewsDetailsAsyncTask extends AsyncTask<String,Integer, ArrayList<CommentBean.DataBean>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CommentBean.DataBean> doInBackground(String... strings) {
            ArrayList<CommentBean.DataBean> list = new HttpUtils().getNewsComment(strings[0]);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<CommentBean.DataBean> list) {
            super.onPostExecute(list);
            commentBeans = list;
            bindData();
        }
    }
    class CommentAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return new HttpUtils().releaseTrend(strings[0],strings[1],strings[2]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("success")) {
                Toast.makeText(NewsDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(NewsDetailActivity.this,"评论失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
    class IsFollowedAsyncTask extends AsyncTask<String,Integer,Boolean>{
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
            if (!result){
                new FollowAsyncTask().execute(userID,authorID);
            }
            else {
                Toast.makeText(NewsDetailActivity.this,"已关注，不要重复点击",Toast.LENGTH_SHORT).show();
            }
        }
    }
    class FollowAsyncTask extends AsyncTask<String,Integer,Boolean> {
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
                Toast.makeText(NewsDetailActivity.this, "关注失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(NewsDetailActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
