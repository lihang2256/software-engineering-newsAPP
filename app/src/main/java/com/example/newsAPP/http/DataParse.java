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
import com.example.newsAPP.bean.TrendCommentBean;
import com.example.newsAPP.bean.UserBean;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * json解析封装
 */
public class DataParse {

    /**
     * 新闻列表解析
     * @param json json
     * @return 新闻列表
     */
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

    /**
     * 新闻评论解析
     * @param json json
     * @return 新闻评论列表
     */
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

    /**
     * 关注列表解析
     * @param json json
     * @return 关注列表
     */
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

    /**
     * 粉丝列表解析
     * @param json json
     * @return 粉丝列表
     */
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

    /**
     * 动态列表解析
     * @param json json
     * @return 动态列表
     */
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

    /**
     * 没有任何返回值，只是看功能是否完成了
     * @param json json
     * @return true
     */
    public static boolean ActionSuccess(String json) {
        Gson gson = new Gson();
        NoDataBean noDataBean = gson.fromJson(json, NoDataBean.class);
        if(noDataBean.getStatus().equals("success")){
            return true;
        }else{
            Log.e(TAG, "actionFailed: Request error");
        }
        return false;
    }

    /**
     * 判断是否为好友，返回bool
     * @param json json
     * @return true/false
     */
    public static boolean isFollowed(String json) {
        Gson gson = new Gson();
        JudgeFriendBean judgeFriendBean = gson.fromJson(json, JudgeFriendBean.class);
        if(judgeFriendBean.getStatus().equals("success")){
            return judgeFriendBean.getIs_friend() == 1;
        }else{
            Log.e(TAG, "actionFailed: Request error");
        }
        return false;
    }

    /**
     * 获取动态评论
     * @param json json
     * @return 动态的评论列表
     */
    public static ArrayList<TrendCommentBean.CommentListBean> TrendComment (String json) {
        Gson gson = new Gson();
        TrendCommentBean bean = gson.fromJson(json, TrendCommentBean.class);
        if(bean.getStatus().equals("success")){
            return (ArrayList<TrendCommentBean.CommentListBean>) bean.getCommentList();
        }else{
            Log.e(TAG, "NewsList: Request error");
        }
        return null;
    }

    /**
     * 获取动态详情
     * @param json json
     * @return 动态详情
     */
    public static ArrayList<TrendCommentBean.DataBean> TrendDetail (String json) {
        Gson gson = new Gson();
        TrendCommentBean bean = gson.fromJson(json, TrendCommentBean.class);
        if(bean.getStatus().equals("success")){
            return (ArrayList<TrendCommentBean.DataBean>) bean.getData();
        }else{
            Log.e(TAG, "NewsList: Request error");
        }
        return null;
    }

    /**
     * 判断登录情况
     * @param json json
     * @return 成功返回用户id/失败返回null
     */
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

    /**
     * 判断注册情况
     * @param json json
     * @return 成功返回success/失败返回null
     */
    public static String signup(String json) {
        Gson gson = new Gson();
        NoDataBean noDataBean= gson.fromJson(json, NoDataBean.class);
        if(noDataBean.getStatus().equals("success")){
            return "success";
        }else{
            Log.e(TAG, "actionFailed: Request error");
        }
        return null;
    }

    public static UserBean random(String json) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(json, UserBean.class);
        if(userBean.getStatus().equals("success")) {
            return userBean;
        }
        else {
            Log.e(TAG, "actionFailed: Request error");
        }
        return null;
    }
}
