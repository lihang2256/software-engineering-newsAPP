package cn.example.newsAPP.fragment.comment;

import android.os.Bundle;
import android.view.View;

import cn.example.newsAPP.fragment.BaseFragment;

public class CommentListFragment extends BaseFragment {

    private String tid; // 图片频道id，用于打开新闻详情页
    private String column;  //   图片的分类

    private View mView;

    private final String TAG = CommentListFragment.class.getSimpleName();
    private static final String KEY_TID = "TID";  //频道id
    private static final String KEY_COLUMN = "COLUMN";
    private static final String SETID = "SETID";  // 图集

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
        bundle.putSerializable(KEY_COLUMN, column);
        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
