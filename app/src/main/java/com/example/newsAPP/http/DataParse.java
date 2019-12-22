package com.example.newsAPP.http;

import android.util.Log;

import com.example.newsAPP.bean.JudgeFriendBean;
import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.bean.NoDataBean;
import com.example.newsAPP.bean.TrendBean;
import com.example.newsAPP.bean.WXZCommentBean;
import com.example.newsAPP.bean.WXZFollowBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.TreeMap;

import static android.content.ContentValues.TAG;

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

    // 新闻评论解析
    public static ArrayList<WXZCommentBean.DataBean> NewsComment(String json) {
        Gson gson = new Gson();
        WXZCommentBean commentBean = gson.fromJson(json, WXZCommentBean.class);
        if(commentBean.getStatus().equals("success")){
            return (ArrayList<WXZCommentBean.DataBean>) commentBean.getData();
        }else{
            Log.e(TAG, "GetNewsAllComment: Request error");
        }
        return null;
    }

    // 关注列表，粉丝列表解析
    public static ArrayList<WXZFollowBean.DataBean> FollowList(String json) {
        Gson gson = new Gson();
        WXZFollowBean followBean = gson.fromJson(json, WXZFollowBean.class);
        if(followBean.getStatus().equals("success")){
            return (ArrayList<WXZFollowBean.DataBean>) followBean.getData();
        }else{
            Log.e(TAG, "FollowBeanParse: Request error");
        }
        return null;
    }

    // 动态列表解析
    public static ArrayList<TrendBean.DataBean> TrendList(String json) {
        Gson gson = new Gson();
        TrendBean trendBean = gson.fromJson(json, TrendBean.class);
        if(trendBean.getStatus().equals("success")){
            return (ArrayList<TrendBean.DataBean>) trendBean.getData();
        }else{
            Log.e(TAG, "TrendBeanParse: Request error");
        }
        return null;
    }

    // 没有任何返回值，只是看功能是否完成了
    public static void ActionSuccess(String json) {
        Gson gson = new Gson();
        NoDataBean noDataBean = gson.fromJson(json, NoDataBean.class);
        if(noDataBean.getStatus().equals("success")){
            return;
        }else{
            Log.e(TAG, "actionFailed: Request error");
        }
        return;
    }

    // 判断是否为好友，返回bool
    public static boolean isFollowed(String json) {
        Gson gson = new Gson();
        JudgeFriendBean judgeFriendBean = gson.fromJson(json, JudgeFriendBean.class);
        if(judgeFriendBean.getStatus().equals("success")){
            if(judgeFriendBean.getIs_friend()==1)
                return true;
            else
                return false;
        }else{
            Log.e(TAG, "actionFailed: Request error");
        }
        return false;
    }

    //
//    public static ArrayList<NewsBean.DataBean> NewsList(String json) {
//        Gson gson = new Gson();
//        NewsBean newsBean = gson.fromJson(json, NewsBean.class);
//        if(newsBean.getStatus().equals("success")){
//            return (ArrayList<NewsBean.DataBean>) newsBean.getData();
//        }else{
//            Log.e(TAG, "NewsList: Request error");
//        }
//        return null;
//    }


}
