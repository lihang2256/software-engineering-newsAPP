package com.example.newsAPP.Utils;

import com.example.newsAPP.common.DatabaseApi;
import com.example.newsAPP.common.FollowSomebodyApi;
import com.example.newsAPP.common.GetNewsCommentApi;
import com.example.newsAPP.common.GetnewsApi;
import com.example.newsAPP.common.SendIdApi;
import com.example.newsAPP.http.OkHttp;

public class HttpUtils {
//    获取新闻列表
    public String getNews(String ...strings){
        OkHttp okHttp = new OkHttp();
        GetnewsApi getnewsApi = new GetnewsApi();
        getnewsApi.setType(strings[0]);
        return okHttp.sendPost(getnewsApi, DatabaseApi.newsList);
    }

//    获取新闻所有评论
    public String getNewsComment(String string){
        OkHttp okHttp = new OkHttp();
        GetNewsCommentApi getNewsComment = new GetNewsCommentApi();
        getNewsComment.setNews_id(string);
        return okHttp.sendPost(getNewsComment, DatabaseApi.getNewsAllComment);
    }

//    获取所有关注的人列表
    public String getFollow(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        return okHttp.sendPost(sendId, DatabaseApi.getFollow);
    }

//    获取粉丝列表
    public String getFans(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        return okHttp.sendPost(sendId, DatabaseApi.getFans);
    }

//    获取所有用户动态
    public String getAllTrend(){
        OkHttp okHttp = new OkHttp();
        return okHttp.sendGet(DatabaseApi.getAllTrend);
    }

//    获取关注的人的动态
    public String getFriendTrend(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        return okHttp.sendPost(sendId, DatabaseApi.getFriendTrend);
    }

//    获取自己的动态
    public String getMyTrend(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        return okHttp.sendPost(sendId, DatabaseApi.getMyTrend);
    }

//    关注用户
    public String follow(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        return okHttp.sendPost(sendId, DatabaseApi.follow);
    }

//    取关用户
    public String unFollow(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        return okHttp.sendPost(sendId, DatabaseApi.unfollow);
    }

//    禁止对方的关注
    public String ban(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        return okHttp.sendPost(sendId, DatabaseApi.ban);
    }

//    判断是否关注
    public String isFollow(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        return okHttp.sendPost(sendId, DatabaseApi.isFollow);
    }


}
