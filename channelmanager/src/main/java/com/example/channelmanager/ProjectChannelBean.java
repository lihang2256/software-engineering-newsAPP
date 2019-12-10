package com.example.channelmanager;

/**
 * Created by Administrator on 2017/2/7.
 * 频道列表
 */

public class ProjectChannelBean {
    // 设置该标签是否可编辑，如果出现在我的频道中，且值为1，则可在右上角显示删除按钮
    private int editStatus;
    //private String cid;
    private String tname;
    //private String ename;
    // 标签类型，显示是我的频道还是更多频道
    private int tabType;
    //private String route;
    //private String column;

    public ProjectChannelBean(){}

    public ProjectChannelBean(String tname){
        this.tname = tname;
    }
//    public ProjectChannelBean(String tname, String route){
//        this.tname = tname;
//        this.route = route;
//    }

//    public ProjectChannelBean(String tname, String column, String route){
//        this.tname = tname;
//        this.column = column;
//        this.route = route;
//    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
    }

    public int getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(int editStatus) {
        this.editStatus = editStatus;
    }

//    public String getCid() {
//        return cid;
//    }
//
//    public void setCid(String cid) {
//        this.cid = cid;
//    }
//
//    public String getEname() {
//        return ename;
//    }
//
//    public void setEname(String ename) {
//        this.ename = ename;
//    }
//
//    public String getRoute() {
//        return route;
//    }
//
//    public void setRoute(String route) {
//        this.route = route;
//    }
//
//    public String getColumn() {
//        return column;
//    }
//
//    public void setColumn(String column) {
//        this.column = column;
//    }
}
