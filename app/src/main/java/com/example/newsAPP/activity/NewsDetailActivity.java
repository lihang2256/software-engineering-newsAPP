package com.example.newsAPP.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newsAPP.R;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.bean.NewsDetailBean;
import com.example.newsAPP.bean.NewsListNormalBean;
import com.example.newsAPP.common.DefineView;
import com.example.newsAPP.widget.LoadingPage;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by liaozhoubei on 2016/12/28.
 */

public class NewsDetailActivity extends BaseActivity implements DefineView {
    private final String TAG = NewsDetailActivity.class.getSimpleName();
    private Context mContext;
    private WebView mWebView;
    private String mUrl;
    private WebSettings mWebSettings;
    private SharedPreferences sharedPreferences;
    private LoadingPage mLoadingPage;
    private ArrayList<CommentBean> commentBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mContext = this;
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("URL");
        initView();
        initValidata();
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_pop_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_1:
                Intent intent = new Intent();
                intent.setClass(NewsDetailActivity.this,CommentDialogActivity.class);
                intent.putExtra("DOCID",mUrl);
                startActivity(intent);
                break;
            case R.id.menu_2:
                Toast.makeText(this, "我是第二个", Toast.LENGTH_SHORT).show();
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
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
