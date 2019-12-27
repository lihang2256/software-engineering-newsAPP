package com.example.newsAPP.Utils;

import com.example.newsAPP.bean.FansBean;
import com.example.newsAPP.bean.FollowBean;
import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.bean.TrendBean;
import com.example.newsAPP.bean.TrendCommentBean;
import com.example.newsAPP.bean.UserBean;
import com.example.newsAPP.common.CollectApi;
import com.example.newsAPP.common.CommentApi;
import com.example.newsAPP.common.DatabaseApi;
import com.example.newsAPP.common.FollowSomebodyApi;
import com.example.newsAPP.common.GetNewsCommentApi;
import com.example.newsAPP.common.GetnewsApi;
import com.example.newsAPP.common.LoginApi;
import com.example.newsAPP.common.ReleaseTrendApi;
import com.example.newsAPP.common.SearchNewsApi;
import com.example.newsAPP.common.SearchTrendApi;
import com.example.newsAPP.common.SendIdApi;
import com.example.newsAPP.common.SignupApi;
import com.example.newsAPP.common.TrendIdApi;
import com.example.newsAPP.common.UserIdApi;
import com.example.newsAPP.http.DataParse;
import com.example.newsAPP.http.OkHttp;

import java.util.ArrayList;

/**
 * 封装端口访问
 */
public class HttpUtils {

    /**
     * 获取新闻列表
     * @param string json
     * @return 新闻列表
     */
    public ArrayList<NewsBean.DataBean> getNews(String string){
        OkHttp okHttp = new OkHttp();
        GetnewsApi getnewsApi = new GetnewsApi();
        getnewsApi.setType(string);
        String strJson = okHttp.sendPost(getnewsApi, DatabaseApi.newsList);
        return DataParse.NewsList(strJson);
    }

    /**
     * 获取新闻所有评论
     * @param string json
     * @return 对应新闻的所有评论列表
     */
    public ArrayList<CommentBean.DataBean> getNewsComment(String string){
        OkHttp okHttp = new OkHttp();
        GetNewsCommentApi getNewsComment = new GetNewsCommentApi();
        getNewsComment.setNews_id(string);
        String strJson = okHttp.sendPost(getNewsComment, DatabaseApi.getNewsAllComment);
        return DataParse.NewsComment(strJson);
    }

    /**
     * 获取所有关注的人列表
     * @param string json
     * @return 关注列表
     */
    public ArrayList<FollowBean.DataBean> getFollow(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.getFollow);
        return DataParse.FollowList(strJson);
    }

    /**
     * 获取粉丝列表
     * @param string json
     * @return 粉丝列表
     */
    public ArrayList<FansBean.DataBean> getFans(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.getFans);
        return DataParse.FansList(strJson);
    }

    /**
     * 获取所有用户动态
     * @return 所有动态列表
     */
    public ArrayList<TrendBean.DataBean> getAllTrend(){
        OkHttp okHttp = new OkHttp();
        String strJson = okHttp.sendGet(DatabaseApi.getAllTrend);
        return DataParse.TrendList(strJson);
    }

    /**
     * 获取关注的人的动态
     * @param string json
     * @return 关注的人的动态列表
     */
    public ArrayList<TrendBean.DataBean> getFriendTrend(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.getFriendTrend);
        return DataParse.TrendList(strJson);
    }

    /**
     * 获取自己的动态
     * @param string json
     * @return 自己动态列表
     */
    public ArrayList<TrendBean.DataBean> getMyTrend(String string){
        OkHttp okHttp = new OkHttp();
        SendIdApi sendId = new SendIdApi();
        sendId.setID(string);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.getMyTrend);
        return DataParse.TrendList(strJson);
    }

    /**
     * 关注用户
     * @param user_id 用户id
     * @param friend_id 另一用户id
     * @return true
     */
    public boolean follow(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        String result =  okHttp.sendPost(sendId, DatabaseApi.follow);
        return DataParse.ActionSuccess(result);
    }

    /**
     * 取关用户
     * @param user_id 用户id
     * @param friend_id 取关用户id
     * @return 取关之后的关注列表
     */
    public ArrayList <FollowBean.DataBean> unFollow(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.unFollow);
        return DataParse.FollowList(strJson);
    }

    /**
     * 拉黑粉丝
     * 因操作需要，两个参数需反着来
     * @param user_id 粉丝id
     * @param friend_id 用户id
     * @return 拉黑之后的粉丝列表
     */
    public ArrayList <FansBean.DataBean> ban(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.ban);
        return DataParse.FansList(strJson);
    }

    /**
     * 判断是否关注
     * @param user_id 用户id
     * @param friend_id 另一用户id
     * @return true/false
     */
    public boolean isFollow(String user_id,String friend_id){
        OkHttp okHttp = new OkHttp();
        FollowSomebodyApi sendId = new FollowSomebodyApi();
        sendId.setUser_id(user_id);
        sendId.setFriend_id(friend_id);
        String strJson = okHttp.sendPost(sendId, DatabaseApi.isFollow);
        return DataParse.isFollowed(strJson);
    }

    /**
     * 获得动态所有评论
     * @param string json
     * @return 动态评论列表
     */
    public ArrayList<TrendCommentBean.CommentListBean> getTrendComment(String string){
        OkHttp okHttp = new OkHttp();
        TrendIdApi trendIdApi = new TrendIdApi();
        trendIdApi.setTrend_id(string);
        String strJson = okHttp.sendPost(trendIdApi, DatabaseApi.getTrendInformation);
        return DataParse.TrendComment(strJson);
    }

    /**
     * 获得动态详细信息
     * @param string json
     * @return 动态详细信息
     */
    public ArrayList<TrendCommentBean.DataBean> getTrendDetail(String string){
        OkHttp okHttp = new OkHttp();
        TrendIdApi trendIdApi = new TrendIdApi();
        trendIdApi.setTrend_id(string);
        String strJson = okHttp.sendPost(trendIdApi, DatabaseApi.getTrendInformation);
        ArrayList<TrendCommentBean.DataBean> beans = DataParse.TrendDetail(strJson);
        return beans;
    }

    /**
     * 发布动态
     * @param user_id 用户id
     * @param content 动态内容
     * @param news_id 评论的新闻id（不是新闻评论的动态默认为1）
     * @return 成功success
     */
    public String releaseTrend(String user_id, String content, String news_id){
        OkHttp okHttp = new OkHttp();
        ReleaseTrendApi releaseTrendApi = new ReleaseTrendApi();
        releaseTrendApi.setUser_id(user_id);
        releaseTrendApi.setContent(content);
        releaseTrendApi.setNews_id(news_id);
        String strJson = okHttp.sendPost(releaseTrendApi, DatabaseApi.releaseTrend);
        return DataParse.signup(strJson);   //解析相同，没有另写
    }

    /**
     * 发布评论
     * @param user_id 用户id
     * @param comment 评论内容
     * @param trend_id 动态id
     * @return
     */
    public String comment(String user_id,String comment, String trend_id){
        OkHttp okHttp = new OkHttp();
        CommentApi commentApi = new CommentApi();
        commentApi.setUser_id(user_id);
        commentApi.setComment(comment);
        commentApi.setTrend_id(trend_id);
        String strJson = okHttp.sendPost(commentApi, DatabaseApi.insertTrendComment);
        return DataParse.signup(strJson);   //解析相同，没有另写
    }

    /**
     * 收藏新闻
     * @param key "query"/"add"/"delete"
     * @param user_id 用户id
     * @param news_id 新闻id
     * @return 查询True/False 收藏/取消成功True
     */
    public String collect(String key,String user_id, String news_id){
        OkHttp okHttp = new OkHttp();
        CollectApi collectApi = new CollectApi();
        collectApi.setKey(key);
        collectApi.setNews(news_id);
        collectApi.setUser(user_id);
        return okHttp.sendPost(collectApi, DatabaseApi.collect);    //返回string，不必解析
    }

    /**
     * 获取收藏
     * @param string json
     * @return 收藏新闻列表
     */
    public ArrayList<NewsBean.DataBean> getCollect(String string){
        OkHttp okHttp = new OkHttp();
        UserIdApi userIdApi = new UserIdApi();
        userIdApi.setUser(string);
        String strJson =  okHttp.sendPost(userIdApi, DatabaseApi.getCollect);
        return DataParse.NewsList(strJson);
    }

    /**
     * 登录
     * @param userName 昵称
     * @param password 密码
     * @return 成功success
     */
    public String login(String userName, String password){
        OkHttp okHttp = new OkHttp();
        LoginApi loginApi = new LoginApi();
        loginApi.setPassword(password);
        loginApi.setId(userName);
        String strJson = okHttp.sendPost(loginApi, DatabaseApi.login);
        return DataParse.login(strJson);
    }

    /**
     * 注册
     * @param userName 昵称
     * @param password 密码
     * @return 成功success
     */
    public String signup(String userName, String password){
        OkHttp okHttp = new OkHttp();
        SignupApi signupApi = new SignupApi();
        signupApi.setPassword(password);
        signupApi.setId(userName);
        String strJson = okHttp.sendPost(signupApi, DatabaseApi.signup);
        return DataParse.signup(strJson);
    }

    /**
     * 搜索新闻
     * @param type 频道
     * @param keyword 关键词
     * @param time 时间
     * @return 新闻列表
     */
    public ArrayList<NewsBean.DataBean> searchNews(String type, String keyword, String time){
        OkHttp okHttp = new OkHttp();
        SearchNewsApi searchNewsApi = new SearchNewsApi();
        searchNewsApi.setType(type);
        searchNewsApi.setKeyword(keyword);
        searchNewsApi.setTime(time);
        String strJson = okHttp.sendPost(searchNewsApi, DatabaseApi.searchNews);
        return DataParse.NewsList(strJson);
    }
    /**
     * 搜索动态
     * @param friend 关注
     * @param keyword 关键词
     * @param time 时间
     * @return 动态列表
     */
    public ArrayList<TrendBean.DataBean> searchTrend(String friend, String keyword, String time){
        OkHttp okHttp = new OkHttp();
        SearchTrendApi searchTrendApi = new SearchTrendApi();
        searchTrendApi.setFriend(friend);
        searchTrendApi.setKeyword(keyword);
        searchTrendApi.setTime(time);
        String strJson = okHttp.sendPost(searchTrendApi, DatabaseApi.searchTrend);
        return DataParse.TrendList(strJson);
    }

    /**
     * 摇一摇获取随机用户
     * @param user_id 用户id
     * @return 随机用户bean
     */
    public UserBean random(String user_id) {
        OkHttp okHttp = new OkHttp();
        UserIdApi userIdApi = new UserIdApi();
        userIdApi.setUser(user_id);
        String strJson = okHttp.sendPost(userIdApi, DatabaseApi.random);
        return DataParse.random(strJson);
    }
}
