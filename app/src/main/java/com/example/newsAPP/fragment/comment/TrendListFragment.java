package com.example.newsAPP.fragment.comment;

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
import com.example.newsAPP.MyApplication;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.activity.TrendDetailActivity;
import com.example.newsAPP.adapter.TrendListAdapter;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.common.DatabaseApi;
import com.example.newsAPP.common.GetnewsApi;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.http.OkHttp;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;

import java.util.ArrayList;

/**
 * Created by LiHang on 2019/11/17
 */
public class TrendListFragment extends BaseFragment {

    private String tname;
    private View mView;
    private final String TAG = TrendListFragment.class.getSimpleName();
    private static final String KEY_TNAME = "TNAME";
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private TrendListAdapter mTrendListAdapter;
    private ArrayList<CommentBean> mTrendBeanList;   // 启动时获得的数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_trend_list, container, false);
        initView();
        initValidata();
        initListener();
        return mView;
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
        //new CommentAsyncTask().execute(tname);
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
                if (mLoadMoreFooterView.canLoadMore() && mTrendListAdapter.getItemCount() > 0) {
                }
            }
        });
    }

    class CommentAsyncTask extends AsyncTask<String,Integer,ArrayList<CommentBean>>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CommentBean> doInBackground(String... strings) {
            OkHttp okHttp = new OkHttp();
            GetnewsApi getnewsApi = new GetnewsApi();
            getnewsApi.setType(strings[0]);
            String result = okHttp.sendPost(getnewsApi, DatabaseApi.newsList);
            ArrayList<CommentBean> list = null;
            //list = DataParse.NewsList(result);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<CommentBean> list) {
            super.onPostExecute(list);
            mTrendBeanList = list;
            bindData();
        }
    }

    @Override
    public void bindData() {
        mTrendListAdapter = new TrendListAdapter(MyApplication.getContext(), mTrendBeanList);
        mIRecyclerView.setIAdapter(mTrendListAdapter);
        // 设置Item点击跳转事件
        mTrendListAdapter.setOnItemClickListener(new TrendListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CommentBean commentBean = mTrendBeanList.get(position);
                Intent intent;
                intent = new Intent(getActivity(), TrendDetailActivity.class);
                intent.putExtra("CID", 1);
                        //commentBean.getID());
                getActivity().startActivity(intent);
            }
        });
    }

    public static TrendListFragment newInstance(String tname){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TNAME, tname);
        TrendListFragment fragment = new TrendListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
