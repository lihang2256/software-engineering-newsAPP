package com.example.newsAPP.fragment.find;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.example.newsAPP.MyApplication;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.activity.NewsDetailActivity;
import com.example.newsAPP.adapter.NewsListAdapter;
import com.example.newsAPP.bean.NewsBean;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.fragment.MineFragment;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;

import java.util.ArrayList;

public class SearchNewsFragment extends BaseFragment {

    private final String TAG = MineFragment.class.getSimpleName();
    private static final String KEY = "TNAME";
    private View mView;
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private NewsListAdapter mNewsListAdapter;
    private ArrayList<NewsBean.DataBean> mNewsBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_list, container,false);
        initView();
        initValidata();
        initListener();
        return mView;
    }

    @Override
    public void initView() {
        mIRecyclerView = mView.findViewById(R.id.iRecyclerView);
        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIRecyclerView.addItemDecoration(new DividerGridItemDecoration(this.getActivity()));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);
    }

    @Override
    public void initValidata() {
    //调用execute()方法，向后端发出请求
        new SearchListAsyncTask().execute();
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
                if (mLoadMoreFooterView.canLoadMore()) {
                    mNewsListAdapter.getItemCount();
                }
            }
        });
    }

    @Override
    public void bindData() {
        if ( mIRecyclerView!=null) {
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
    }

    /**
     * 搜索 新闻 接口
     * 异步方法，获取并渲染
     */
    class SearchListAsyncTask extends AsyncTask<String,Integer,ArrayList<NewsBean.DataBean>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<NewsBean.DataBean> doInBackground(String... strings) {
            return new HttpUtils().searchNews(
                    //接收到FindListFragment向此fragment传的值，并向后端post
                    SharedPreferenceUtils.getInstance().getString(getActivity(),"SEARCHTYPE",null),
                    SharedPreferenceUtils.getInstance().getString(getActivity(),"NEWSINPUT",""),
                    SharedPreferenceUtils.getInstance().getString(getActivity(),"SEARCHTIME",null)
            );
        }

        @Override
        protected void onPostExecute(ArrayList<NewsBean.DataBean> list) {
            super.onPostExecute(list);
            mNewsBeanList = list;
            bindData();
        }
    }
}
