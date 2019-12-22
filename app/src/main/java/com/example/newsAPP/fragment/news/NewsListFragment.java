package com.example.newsAPP.fragment.news;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import java.util.ArrayList;

import com.example.newsAPP.MyApplication;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.activity.NewsDetailActivity;
import com.example.newsAPP.adapter.NewsListAdapter;
import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.common.DatabaseApi;
import com.example.newsAPP.common.GetnewsApi;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.http.DataParse;
import com.example.newsAPP.http.OkHttp;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;

public class NewsListFragment extends BaseFragment {

    private final String TAG = NewsListFragment.class.getSimpleName();
    private static final String KEY = "TNAME";
    private String tname;
    private View mView;
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private NewsListAdapter mNewsListAdapter;
    private ArrayList<NewsBean.DataBean> mNewsBeanList = new ArrayList<>();

    /**
     * 从外部往Fragment中传参数的方法
     *
     * @param tname 请求标题名字
     * @return
     */
    public static NewsListFragment newInstance(String tname) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY, tname);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView();
        initValidata();
        initListener();
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void initView() {
        mIRecyclerView = mView.findViewById(R.id.iRecyclerView);
        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);
    }

    @Override
    public void initValidata() {
        if (getArguments() != null) {
            tname = getArguments().getString("TNAME");
        }
        new NewsAsyncTask().execute(tname);
    }

    class NewsAsyncTask extends AsyncTask<String,Integer,ArrayList<NewsBean.DataBean>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<NewsBean.DataBean> doInBackground(String... strings) {
            ArrayList<NewsBean.DataBean> list = new HttpUtils().getNews(strings[0]);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsBean.DataBean> list) {
            super.onPostExecute(list);
            mNewsBeanList = list;
            bindData();
        }
    }

    @Override
    public void bindData() {
        mNewsListAdapter = new NewsListAdapter(MyApplication.getContext(), mNewsBeanList);
        mIRecyclerView.setIAdapter(mNewsListAdapter);
        // 设置Item点击跳转事件
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                NewsBean.DataBean bean = mNewsBeanList.get(position);
                Intent intent;
                intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("URL", bean.getUrl());
                intent.putExtra("NEWSID",bean.getId());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {
        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mNewsListAdapter.getItemCount() > 0) {
                }
            }
        });
    }
}
