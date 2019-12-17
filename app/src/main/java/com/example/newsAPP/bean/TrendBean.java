package com.example.newsAPP.bean;

import java.util.List;

public class TrendBean {
    /**
     * status : success
     * data : [{"release_time":"1998-08-21","comment_num":5,"context":"fuckyou"}]
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
         * release_time : 1998-08-21
         * comment_num : 5
         * context : fuckyou
         */

        private String release_time;
        private int comment_num;
        private String context;

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }
}
