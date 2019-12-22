package com.example.newsAPP.bean;

import java.util.List;

//也就是CommentDetailBean
public class TrendCommentBean {

    /**
     * commentList : [{"user_id":29,"nick_name":"fxiaoyu","content":"亲人之间也有世态炎凉","release_time":"2019-12-14 22:07:03"},{"user_id":26,"nick_name":"cxiaoyu","content":"看了新闻我自闭了","release_time":"2019-12-19 21:36:32"}]
     * data : [{"nick_name":"bxiaoyu","ID":1,"author_id":25,"title":"达州市达川区一老人多年无户籍 派出所民警实地走访解难题","content":"真的难受这条新闻","news_id":1,"url":"http://mini.eastday.com/mobile/191126203843884.html","release_time":"2019-12-10 23:34:13"}]
     * status : success
     */

    private String status;
    private List<CommentListBean> commentList;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class CommentListBean {
        /**
         * user_id : 29
         * nick_name : fxiaoyu
         * content : 亲人之间也有世态炎凉
         * release_time : 2019-12-14 22:07:03
         */

        private int user_id;
        private String nick_name;
        private String content;
        private String release_time;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }
    }

    public static class DataBean {
        /**
         * nick_name : bxiaoyu
         * ID : 1
         * author_id : 25
         * title : 达州市达川区一老人多年无户籍 派出所民警实地走访解难题
         * content : 真的难受这条新闻
         * news_id : 1
         * url : http://mini.eastday.com/mobile/191126203843884.html
         * release_time : 2019-12-10 23:34:13
         */

        private String nick_name;
        private int ID;
        private int author_id;
        private String title;
        private String content;
        private int news_id;
        private String url;
        private String release_time;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }
    }
}
