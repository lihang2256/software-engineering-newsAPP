package com.example.newsAPP.fragment.trend;

import android.content.Context;
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
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.activity.TrendDetailActivity;
import com.example.newsAPP.adapter.TrendListAdapter;
import com.example.newsAPP.bean.TrendBean;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;

import java.util.ArrayList;

public class TrendListFragment extends BaseFragment {

    private String ttype;
    private View mView;
    private final String TAG = TrendListFragment.class.getSimpleName();
    private static final String KEY_TNAME = "TTYPE";
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private TrendListAdapter mTrendListAdapter;
    private ArrayList<TrendBean.DataBean> mTrendBeanList;   // 启动时获得的数据
    private Context mContext;
    private String userID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_trend_list, container, false);
        mContext = getActivity();
        userID = SharedPreferenceUtils.getInstance().getString(mContext,"USERID",null);
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
            ttype = getArguments().getString("TTYPE");
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mTrendBeanList = new HttpUtils().getAllTrend();
//            }
//        }).start();
//        bindData();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ArrayList<TrendBean.DataBean> list;
//                if (userID == null){
//                    if (ttype.equals("所有")) {
//                        list = new HttpUtils().getAllTrend();
//                    }
//                    else if (ttype.equals("关注")) {
//                        list = null;
//                    }
//                    else {
//                        list = null;
//                    }
//                }
//                else {
//                    if (ttype.equals("所有")) {
//                        list = new HttpUtils().getAllTrend();
//                    }
//                    else if (ttype.equals("关注")) {
//                        list = new HttpUtils().getFriendTrend(userID);
//                    }
//                    else {
//                        list = new HttpUtils().getMyTrend(userID);
//                    }
//                }
//                mTrendBeanList = list;
//                bindData();
//            }
//        }).start();
        new TrendAsyncTask().execute(ttype,userID);
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

    class TrendAsyncTask extends AsyncTask<String,Integer,ArrayList<TrendBean.DataBean>>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TrendBean.DataBean> doInBackground(String... strings) {
            ArrayList<TrendBean.DataBean> list;
            if (strings[1] == null){
                if (strings[0].equals("所有")) {
                    list = new HttpUtils().getAllTrend();
                }
                else if (strings[0].equals("关注")) {
                    list = null;
                }
                else {
                    list = null;
                }
            }
            else {
                if (strings[0].equals("所有")) {
                    list = new HttpUtils().getAllTrend();
                }
                else if (strings[0].equals("关注")) {
                    list = new HttpUtils().getFriendTrend(strings[1]);
                }
                else {
                    list = new HttpUtils().getMyTrend(strings[1]);
                }
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<TrendBean.DataBean> list) {
            super.onPostExecute(list);
            mTrendBeanList = list;
            bindData();
        }
    }

    @Override
    public void bindData() {
        if (mTrendBeanList != null) {
            mTrendListAdapter = new TrendListAdapter(MyApplication.getContext(), mTrendBeanList);
            mIRecyclerView.setIAdapter(mTrendListAdapter);
            // 设置Item点击跳转事件
            mTrendListAdapter.setOnItemClickListener(new TrendListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    TrendBean.DataBean bean = mTrendBeanList.get(position);
                    Intent intent;
                    intent = new Intent(getActivity(), TrendDetailActivity.class);
                    intent.putExtra("TRENDID", bean.getID());
                    intent.putExtra("TAUTHORID",bean.getAuthor_id());
                    intent.putExtra("TNAME",bean.getNick_name());
                    intent.putExtra("TTIME",bean.getRelease_time());
                    intent.putExtra("TCONTENT",bean.getContent());
                    intent.putExtra("TNEWSID",bean.getNews_id());
                    intent.putExtra("TNEWSTITLE",bean.getTitle());
                    intent.putExtra("TNEWSURL",bean.getUrl());
                    getActivity().startActivity(intent);
                }
            });
        }
    }

    public static TrendListFragment newInstance(String tname){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TNAME, tname);
        TrendListFragment fragment = new TrendListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
