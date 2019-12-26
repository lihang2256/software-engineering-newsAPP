package com.example.newsAPP.Utils;

import com.example.channelmanager.ProjectChannelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 事先存放目录
 */
public class CategoryDataUtils {
    /**
     * 初始新闻频道
     * @return 新闻频道列表
     */
    public static List<ProjectChannelBean> getChannelCategoryBeans(){
        List<ProjectChannelBean>  beans=new ArrayList<>();
        beans.add(new ProjectChannelBean("头条"));
        beans.add(new ProjectChannelBean("社会"));
        beans.add(new ProjectChannelBean("国内"));
        beans.add(new ProjectChannelBean("国际"));
        beans.add(new ProjectChannelBean("娱乐"));
        return beans;
    }

    /**
     * 初始更多新闻频道
     * @return 新闻频道列表
     */
    public static List<ProjectChannelBean> getMoreCategoryBeans(){
        List<ProjectChannelBean>  beans=new ArrayList<>();
        beans.add(new ProjectChannelBean("体育"));
        beans.add(new ProjectChannelBean("军事"));
        beans.add(new ProjectChannelBean("科技"));
        beans.add(new ProjectChannelBean("时尚"));
        return beans;
    }

    /**
     * 动态标签
     * @return 动态标签列表
     */
    public static List<ProjectChannelBean> getTrendCategoryBeans(){
        List<ProjectChannelBean> beans = new ArrayList<>();
        beans.add(new ProjectChannelBean("所有"));
        beans.add(new ProjectChannelBean("关注"));
        beans.add(new ProjectChannelBean("我的"));
        return beans;
    }

    /**
     * 搜索标签
     * @return 搜索标签列表
     */
    public static List<ProjectChannelBean> getFindCategoryBeans(){
        List<ProjectChannelBean> beans = new ArrayList<>();
        beans.add(new ProjectChannelBean("新闻搜索"));
        beans.add(new ProjectChannelBean("动态搜索"));
        return beans;
    }
}
