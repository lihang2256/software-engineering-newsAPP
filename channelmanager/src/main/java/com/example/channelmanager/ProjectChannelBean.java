package com.example.channelmanager;

public class ProjectChannelBean {
    // 设置该标签是否可编辑，如果出现在我的频道中，且值为1，则可在右上角显示删除按钮
    private int editStatus;
    private String tname;
    // 标签类型，显示是我的频道还是更多频道
    private int tabType;

    public ProjectChannelBean() {}
    public ProjectChannelBean(String tname){
        this.tname = tname;
    }

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

}
