package com.example.newsAPP.common;

public class CommentApi {

    /**
     * user_id : 26
     * trend_id : 2
     * comment : 秀儿是你吗？
     */

    private String user_id;
    private String trend_id;
    private String comment;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTrend_id() {
        return trend_id;
    }

    public void setTrend_id(String trend_id) {
        this.trend_id = trend_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
