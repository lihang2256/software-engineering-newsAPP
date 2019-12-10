package com.example.newsAPP.bean;

public class NewsBean {
    private String title; // 分类Tab名称
    private String tid; // 分类类型

    public NewsBean(){
        super();
    }

    public NewsBean(String title, String tid) {
        this.title = title;
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTid() {
        return tid;
    }

    public void setTid(String data_type) {
        this.tid = tid;
    }
}
