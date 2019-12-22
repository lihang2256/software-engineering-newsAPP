package com.example.newsAPP.http;

import android.util.Log;

import com.example.newsAPP.bean.FansBean;
import com.example.newsAPP.bean.FollowBean;
import com.example.newsAPP.bean.JudgeFriendBean;
import com.example.newsAPP.bean.LoginBean;
import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.bean.NoDataBean;
import com.example.newsAPP.bean.TrendBean;
import com.example.newsAPP.bean.CommentBean;
import com.google.gson.Gson;

import java.util.ArrayList;

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
    public static ArrayList<CommentBean.DataBean> NewsComment(String json) {
        Gson gson = new Gson();
        CommentBean commentBean = gson.fromJson(json, CommentBean.class);
        if(commentBean.getStatus().equals("success")){
            return (ArrayList<CommentBean.DataBean>) commentBean.getData();
        }else{
            Log.e(TAG, "GetNewsAllComment: Request error");
        }
        return null;
    }

    // 关注列表解析
    public static ArrayList<FollowBean.DataBean> FollowList(String json) {
        Gson gson = new Gson();
       FollowBean followBean = gson.fromJson(json, FollowBean.class);
        if(followBean.getStatus().equals("success")){
            return (ArrayList<FollowBean.DataBean>) followBean.getData();
        }else{
            Log.e(TAG, "FollowBeanParse: Request error");
        }
        return null;
    }

    // 粉丝列表解析
    public static ArrayList<FansBean.DataBean> FansList(String json) {
        Gson gson = new Gson();
        FansBean fansBean = gson.fromJson(json, FansBean.class);
        if(fansBean.getStatus().equals("success")){
            return (ArrayList<FansBean.DataBean>) fansBean.getData();
        }else{
            Log.e(TAG, "FansBeanParse: Request error");
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

//    TODO：动态详细页面解析，不知道要啥

    public static String login(String json) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        if(loginBean.getStatus().equals("success")){
            return loginBean.getId();
        }else{
            Log.e(TAG, "actionFailed: Request error");
        }
        return null;
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
