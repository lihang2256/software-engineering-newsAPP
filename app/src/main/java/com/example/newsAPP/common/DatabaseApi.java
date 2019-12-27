package com.example.newsAPP.common;

public class DatabaseApi {
    //端口地址
    public static final String host = "http://47.100.79.111:8080/";
    //获取新闻列表
    public static final String newsList = "getNews";
    //关注列表
    public static final String getFollow = "getFriend";
    //获得所有动态
    public static final String getAllTrend = "getAllTrend";
    //获得关注动态
    public static final String getFriendTrend = "getFriendTrend";
    //获得我的动态
    public static final String getMyTrend = "getMyTrend";
    //粉丝列表
    public static final String getFans = "getFans";
    //关注
    public static final String follow = "insertInterest";
    //取消关注
    public static final String unFollow = "deleteFriend";
    //拉黑粉丝
    public static final String ban = "cancelInterest";
    //判断是否是好友
    public static final String isFollow = "judgeFriend";
    //收藏相关的各中端口
    public static final String collect = "collect";
    //获取收藏列表
    public static final String getCollect = "getCollect";
    //登录
    public static final String login = "login";
    //搜索新闻
    public static final String searchNews = "searchNews";
    //搜索动态
    public static final String searchTrend = "searchTrend";
    //获取新闻评论
    public static final String getNewsAllComment = "getNewsAllComment";
    //获取动态详情和动态评论
    public static final String getTrendInformation = "getTrendInformation";
    //发布动态/发布新闻评论
    public static final String releaseTrend = "releaseTrend";
    //发布动态评论
    public static final String insertTrendComment = "insertTrendComment";
    //注册
    public static final String signup = "register";
    //摇一摇获取用户
    public static final String random = "random";
}
