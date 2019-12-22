package com.example.newsAPP.bean;

import java.util.List;

//这个获取新闻的所有评论
public class CommentBean {

    /**
     * data : [{"user_id":25,"nick_name":"bxiaoyu","content":"中美贸易摩擦你们怎么看","release_time":"2019-12-05 15:35:46"},{"user_id":27,"nick_name":"dxiaoyu","content":"这条新闻你们怎么看","release_time":"2019-12-14 13:50:12"},{"user_id":25,"nick_name":"bxiaoyu","content":"令人深思","release_time":"2019-12-14 13:51:52"}]
     * status : success
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 25
         * nick_name : bxiaoyu
         * content : 中美贸易摩擦你们怎么看
         * release_time : 2019-12-05 15:35:46
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
}
