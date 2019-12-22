package com.example.newsAPP.Utils;

import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.bean.TrendCommentBean;
import com.example.newsAPP.common.CollectApi;
import com.example.newsAPP.common.CommentApi;
import com.example.newsAPP.common.DatabaseApi;
import com.example.newsAPP.common.FollowSomebodyApi;
import com.example.newsAPP.common.GetNewsCommentApi;
import com.example.newsAPP.common.GetnewsApi;
import com.example.newsAPP.common.LoginApi;
import com.example.newsAPP.common.ReleaseTrendApi;
import com.example.newsAPP.common.SearchNewsApi;
import com.example.newsAPP.common.SendIdApi;
import com.example.newsAPP.common.TrendIdApi;
import com.example.newsAPP.common.UserIdApi;
import com.example.newsAPP.http.DataParse;
import com.example.newsAPP.http.OkHttp;

import java.nio.file.attribute.UserPrincipal;

import java.util.ArrayList;

public class HttpUtils {
//    获取新闻列表
    public ArrayList<NewsBean.DataBean> getNews(String string){
        OkHttp okHttp = new OkHttp();
        GetnewsApi getnewsApi = new GetnewsApi();
        getnewsApi.setType(string);
        String strJson = okHttp.sendPost(getnewsApi, DatabaseApi.newsList);
        ArrayList<NewsBean.DataBean> beans = DataParse.NewsList(strJson);
        return beans;
    }

//    获取新闻所有评论
    public ArrayList<CommentBean.DataBean> getNewsComment(String string){
        OkHttp okHttp = new OkHttp();
        GetNewsCommentApi getNewsComment = new GetNewsCommentApi();
        getNewsComment.setNews_id(string);
        String strJson = okHttp.sendPost(getNewsComment, DatabaseApi.getNewsAllComment);
        ArrayList<CommentBean.DataBean> beans = DataParse.NewsComment(strJson);
        return beans;
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

//     获得动态的评论和详细信息
    public String getTrendComment(String string){
        OkHttp okHttp = new OkHttp();
        TrendIdApi trendIdApi = new TrendIdApi();
        trendIdApi.setTrend_id(string);
        return okHttp.sendPost(trendIdApi, DatabaseApi.getTrendInformation);
    }

//    发布动态
    public String releaseTrend(String user_id, String content){
        OkHttp okHttp = new OkHttp();
        ReleaseTrendApi releaseTrendApi = new ReleaseTrendApi();
        releaseTrendApi.setUser_id(user_id);
        releaseTrendApi.setContent(content);
        return okHttp.sendPost(releaseTrendApi, DatabaseApi.releaseTrend);
    }

//    发送评论
    public String comment(String user_id,String comment, String trend_id){
        OkHttp okHttp = new OkHttp();
        CommentApi commentApi = new CommentApi();
        commentApi.setUser_id(user_id);
        commentApi.setComment(comment);
        commentApi.setTrend_id(trend_id);
        return okHttp.sendPost(commentApi, DatabaseApi.insertTrendComment);
    }

//    收藏新闻
    public String collect(String key,String user_id, String news_id){
        OkHttp okHttp = new OkHttp();
        CollectApi collectApi = new CollectApi();
        collectApi.setKey(key);
        collectApi.setNews(news_id);
        collectApi.setUser(user_id);
        return okHttp.sendPost(collectApi, DatabaseApi.collect);
    }

//    获取收藏
    public String getCollect(String string){
        OkHttp okHttp = new OkHttp();
        UserIdApi userIdApi = new UserIdApi();
        userIdApi.setUser(string);
        return okHttp.sendPost(userIdApi, DatabaseApi.getCollect);
    }

//    登陆
    public String login(String userName, String password){
        OkHttp okHttp = new OkHttp();
        LoginApi loginApi = new LoginApi();
        loginApi.setPassword(password);
        loginApi.setId(userName);
        return okHttp.sendPost(loginApi, DatabaseApi.login);

    }
//    搜索新闻
    public String searchNews(String type, String keyword, String time){
        OkHttp okHttp = new OkHttp();
        SearchNewsApi searchNewsApi = new SearchNewsApi();
        searchNewsApi.setType(type);
        searchNewsApi.setKeyword(keyword);
        searchNewsApi.setTime(time);
        return okHttp.sendPost(searchNewsApi, DatabaseApi.searchNews);
    }
}
