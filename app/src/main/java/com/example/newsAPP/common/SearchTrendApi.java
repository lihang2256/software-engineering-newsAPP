package com.example.newsAPP.common;

public class SearchTrendApi {
    /**
     * friend: XiaoMing
     * keyword : aaa
     * time : aaa
     */

    private String friend;
    private String keyword;
    private String time;

    public String getFriend() {
        return friend;
    }

    public void setFriend(String type) {
        this.friend = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
