package com.example.newsAPP.fragment.comment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.Utils.LogUtils;
import com.example.newsAPP.Utils.ThreadManager;
import com.example.newsAPP.adapter.CommentListAdapter;
import com.example.newsAPP.bean.NewsListNormalBean;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
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
    private CommentListAdapter mCommentListAdapter;
    private LoadingPage mLoadingPage;

    private List<NewsListNormalBean> mNewsListNormalBeanList;   // 启动时获得的数据
    private List<NewsListNormalBean> newlist;   // 上拉刷新后获得的数据

    private int mStartIndex = 0;    // 请求数据的起始参数
    public ThreadManager.ThreadPool mThreadPool; // 线程池
    private boolean isPullRefresh;  // 判断当前是下拉刷新还是上拉刷新
    private boolean isShowCache = false; // 是否有缓存数据被展示
    private boolean isConnectState = false;  // 判断当前是否在联网刷新, false表示当前没有联网刷新


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_comment_list, container, false);
        initView();
        initValidata();
        initListener();
        LogUtils.d(TAG, "调用了onCreateView" + tid);
        return mView;
    }

    @Override
    public void initView() {
        mLoadingPage = (LoadingPage) mView.findViewById(R.id.loading_page);
        mIRecyclerView = (IRecyclerView) mView.findViewById(R.id.iRecyclerView);

        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        // we can set view
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);
        //showLoadingPage();
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

    public void requestData(){

    }
}
