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
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.newsAPP.R;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.common.DefineView;

import java.util.ArrayList;

public class NewsDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private int mID;
    private Context mContext;
    private WebView mWebView;
    private String mUrl;
    private SharedPreferences sharedPreferences;
    private ArrayList<CommentBean> commentBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mContext = this;
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("URL");
        //mID = intent.getIntExtra("ID",-1);
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
                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                View view = LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.dialog, null);
                //    设置我们自己定义的布局文件作为弹出框的Content
                builder.setView(view);
                final EditText password = (EditText)view.findViewById(R.id.comment_commit_content);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String s = password.getText().toString().trim();
                        //    将输入的用户名和密码打印出来
                        Toast.makeText(NewsDetailActivity.this, "评论: " + s , Toast.LENGTH_SHORT).show();
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
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder1 = new AlertDialog.Builder(NewsDetailActivity.this);
                //    设置Title的图标
                //builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder1.setTitle("关注");
                //    设置Content来显示一个信息
                builder1.setMessage("是否关注"+"pighao");
                //    设置一个PositiveButton
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(NewsDetailActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NegativeButton
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(NewsDetailActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NeutralButton
//`
                //    显示出该对话框
                builder1.show();
                //add something
                //Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
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
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
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

    @Override
    public void initValidata() {
        new NewsDetailsAsyncTask().execute();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
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
//            @Override
//            public void onProgressChanged(WebView view, String url) {
//                super.onProgressChanged(view, url);
//                view.loadUrl("javascript:function mFunc(){" +
//                        "var array = document.querySelectorAll('.top-wrap');" +
//                        "for(var i=0;i<array.length;i+=1){" +
//                        "array[i].style.display='none';" +
//                        "}" +
//                        "}" +
//                        "mFunc();");
//                view.loadUrl("javascript:function mFunc(){" +
//                        "var array = document.querySelectorAll('.articledown-wrap');" +
//                        "for(var i=0;i<array.length;i+=1){" +
//                        "array[i].style.display='none';" +
//                        "}" +
//                        "}" +
//                        "mFunc();");
//                view.loadUrl("javascript:function mFunc(){" +
//                        "var array = document.querySelectorAll('#news_check');" +
//                        "for(var i=0;i<array.length;i+=1){" +
//                        "array[i].style.display='none';" +
//                        "}" +
//                        "}" +
//                        "mFunc();");
//            }
        });
        mWebView.loadUrl(mUrl);
    }

    class NewsDetailsAsyncTask extends AsyncTask<String,Integer, ArrayList<CommentBean>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CommentBean> doInBackground(String... strings) {
            ArrayList<CommentBean> list;
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<CommentBean> list) {
            super.onPostExecute(list);
            commentBeans = list;
            bindData();
        }
    }
}
