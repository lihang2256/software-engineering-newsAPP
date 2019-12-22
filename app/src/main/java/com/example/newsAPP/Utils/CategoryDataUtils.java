package com.example.newsAPP.Utils;

import com.example.channelmanager.ProjectChannelBean;

import java.util.ArrayList;
import java.util.List;

public class CategoryDataUtils {
    public static List<ProjectChannelBean> getChannelCategoryBeans(){
        List<ProjectChannelBean>  beans=new ArrayList<>();
        beans.add(new ProjectChannelBean("头条"));
        beans.add(new ProjectChannelBean("社会"));
        beans.add(new ProjectChannelBean("国内"));
        beans.add(new ProjectChannelBean("国际"));
        beans.add(new ProjectChannelBean("娱乐"));
        return beans;
    }

    public static List<ProjectChannelBean> getMoreCategoryBeans(){
        List<ProjectChannelBean>  beans=new ArrayList<>();
        beans.add(new ProjectChannelBean("体育"));
        beans.add(new ProjectChannelBean("军事"));
        beans.add(new ProjectChannelBean("科技"));
        beans.add(new ProjectChannelBean("时尚"));
        return beans;
    }
    public static List<ProjectChannelBean> getTrendCategoryBeans(){
        List<ProjectChannelBean> beans = new ArrayList<>();
        beans.add(new ProjectChannelBean("所有"));
        beans.add(new ProjectChannelBean("关注"));
        beans.add(new ProjectChannelBean("我的"));
        return beans;
    }
    public static List<ProjectChannelBean> getFindCategoryBeans(){
        List<ProjectChannelBean> beans = new ArrayList<>();
        beans.add(new ProjectChannelBean("新闻搜索"));
        beans.add(new ProjectChannelBean("动态搜索"));
        return beans;
    }
}
