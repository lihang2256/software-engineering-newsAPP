package com.example.newsAPP.http;

import android.util.Log;

import com.example.newsAPP.bean.NewsBean;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Bei on 2017/1/6.
 * 解析Json数据的工具类
 */

public class DataParse {

    // 新闻列表解析
    public static ArrayList<NewsBean.DataBean> NewsList(String json) {
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(json, NewsBean.class);
        if(newsBean.getStatus().equals("success")){
            return (ArrayList<NewsBean.DataBean>) newsBean.getData();
        }else{
            Log.e(TAG, "NewsList: Request error");
        }
        return null;
    }

    //
    public static ArrayList<NewsBean.DataBean> NewsList(String json) {
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(json, NewsBean.class);
        if(newsBean.getStatus().equals("success")){
            return (ArrayList<NewsBean.DataBean>) newsBean.getData();
        }else{
            Log.e(TAG, "NewsList: Request error");
        }
        return null;
    }


}
