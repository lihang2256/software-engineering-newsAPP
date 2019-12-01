package com.example.newsAPP.fragment.comment;

import android.os.Bundle;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.example.newsAPP.Utils.ThreadManager;
import com.example.newsAPP.adapter.NewsListAdapter;
import com.example.newsAPP.bean.NewsListNormalBean;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.widget.LoadMoreFooterView;
import com.example.newsAPP.widget.LoadingPage;

import java.util.List;

/**
 * Created by LiHang on 2019/11/17
 */
public class CommentListFragment extends BaseFragment {

    private String tid; // 动态频道id
    private View mView;
    private final String TAG = CommentListFragment.class.getSimpleName();
    private static final String KEY_TID = "TID";  //频道id
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private NewsListAdapter mNewsListAdapter;
    private LoadingPage mLoadingPage;

    private List<NewsListNormalBean> mNewsListNormalBeanList;   // 启动时获得的数据
    private List<NewsListNormalBean> newlist;   // 上拉刷新后获得的数据

    private int mStartIndex = 0;    // 请求数据的起始参数
    public ThreadManager.ThreadPool mThreadPool; // 线程池
    private boolean isPullRefresh;  // 判断当前是下拉刷新还是上拉刷新
    private boolean isShowCache = false; // 是否有缓存数据被展示
    private boolean isConnectState = false;  // 判断当前是否在联网刷新, false表示当前没有联网刷新






    @Override
    public void initView() {

    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    public static CommentListFragment newInstance(String tid, String column){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TID, tid);
        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
