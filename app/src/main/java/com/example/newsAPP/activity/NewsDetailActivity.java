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
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.common.DefineView;

import java.util.ArrayList;

public class NewsDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private int mID;
    private Context mContext;
    private WebView mWebView;
    private String mUrl;
    private int id;
    private ArrayList<CommentBean> commentBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mContext = this;
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("URL");
        id = (int)SharedPreferenceUtils.getInstance().get(mContext,"USERID",-100);
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
                View view = LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.dialog, null);
                builder.setView(view);
                final EditText password = (EditText)view.findViewById(R.id.comment_commit_content);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //add something
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(NewsDetailActivity.this, "positive: " + id, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.menu_2:
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(NewsDetailActivity.this);
//                //    设置Title的图标
//                //builder.setIcon(R.drawable.ic_launcher);
//                //    设置Title的内容
//                builder1.setTitle("关注");
//
//                builder1.setMessage("是否关注"+"pighao");
//                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        Toast.makeText(NewsDetailActivity.this, "positive: " + id, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                //    设置一个NegativeButton
//                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        Toast.makeText(NewsDetailActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder1.show();
                //判断
                boolean flag = true;
                if (flag){
                    Toast.makeText(NewsDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewsDetailActivity.this,"已收藏，不要重复点击",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_3:
                boolean fflag = true;
                if (fflag){
                    Toast.makeText(NewsDetailActivity.this,"未收藏，不可取消收藏",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewsDetailActivity.this,"取消收藏成功",Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public void initValidata() {
        new NewsDetailsAsyncTask().execute();
    }

    @Override
    public void initListener() {

    }

    /**
     * 去除网页内的广告
     */
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
