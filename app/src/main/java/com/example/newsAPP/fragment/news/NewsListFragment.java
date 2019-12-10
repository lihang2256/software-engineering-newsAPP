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
import java.util.List;

import com.example.newsAPP.MyApplication;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.activity.NewsDetailActivity;
import com.example.newsAPP.adapter.NewsListAdapter;
import com.example.newsAPP.bean.NewsListNormalBean;
import com.example.newsAPP.common.DatabaseApi;
import com.example.newsAPP.common.GetnewsApi;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.http.DataParse;
import com.example.newsAPP.http.OkHttp;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;
import com.example.newsAPP.widget.LoadingPage;

/**
 * Created by Bei on 2016/12/25.
 */

public class NewsListFragment extends BaseFragment {
    private final String TAG = NewsListFragment.class.getSimpleName();
    private static final String KEY = "TNAME";
    private String tname;
    private View mView;
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private NewsListAdapter mNewsListAdapter;
    //private LoadingPage mLoadingPage;
    private ArrayList<NewsListNormalBean.DataBean> mNewsListNormalBeanList = new ArrayList<>();   // 启动时获得的数据
    private List<NewsListNormalBean.DataBean> newslist;   // 上拉刷新后获得的数据

    private boolean isPullRefresh;  // 判断当前是下拉刷新还是上拉刷新

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
        //mLoadingPage = (LoadingPage) mView.findViewById(R.id.loading_page);
        mIRecyclerView = (IRecyclerView) mView.findViewById(R.id.iRecyclerView);
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

    class NewsAsyncTask extends AsyncTask<String,Integer,ArrayList<NewsListNormalBean.DataBean>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<NewsListNormalBean.DataBean> doInBackground(String... strings) {
            OkHttp okHttp = new OkHttp();
            GetnewsApi getnewsApi = new GetnewsApi();
            getnewsApi.setType(strings[0]);
            String result = okHttp.sendPost(getnewsApi, DatabaseApi.newsList);
//            NewsListNormalBean.DataBean dataBean = new NewsListNormalBean.DataBean();
//            dataBean.setType("头条");
//            dataBean.setTitle("达州市达川区一老人多年无户籍 派出所民警实地走访解难题");
//            dataBean.setTime("2019-11-26 20:38:00");
//            dataBean.setPicture("http://08imgmini.eastday.com/mobile/20191126/20191126203843_6bf761175986aa2d2728710014cd83cf_1_mwpm_03200403.jpg");
//            dataBean.setAuthor("中国新闻网");
//            dataBean.setUrl("http://mini.eastday.com/mobile/191126203843884.html");
            ArrayList<NewsListNormalBean.DataBean> list;
            //list.add(dataBean);
            list = DataParse.NewsList(result);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsListNormalBean.DataBean> list) {
            super.onPostExecute(list);
            mNewsListNormalBeanList = list;
            bindData();
        }
    }

    @Override
    public void bindData() {
        mNewsListAdapter = new NewsListAdapter(MyApplication.getContext(), mNewsListNormalBeanList);
        mIRecyclerView.setIAdapter(mNewsListAdapter);
        // 设置Item点击跳转事件
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                NewsListNormalBean.DataBean bean = mNewsListNormalBeanList.get(position);
                Intent intent;
                intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("URL", bean.getUrl());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {
        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                DownToRefresh();
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mNewsListAdapter.getItemCount() > 0) {
                    PullUpToRefresh();
                }
            }
        });
    }

    // 下拉刷新
    public void DownToRefresh() {
    }

    // 上拉刷新
    public void PullUpToRefresh() {
    }


    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {

    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的数据加载方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {
            // 是下拉刷新，目前无法刷新到新数据
            newslist.addAll(mNewsListNormalBeanList);
            mNewsListNormalBeanList.removeAll(mNewsListNormalBeanList);
            mNewsListNormalBeanList.addAll(newslist);
            mNewsListAdapter.notifyDataSetChanged();
        } else {
            // 上拉刷新
            mNewsListNormalBeanList.addAll(newslist);
            mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        }
        mNewsListAdapter.notifyDataSetChanged();
    }
}
