package cn.bproject.neteasynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.channelmanager.ProjectChannelBean;

import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.adapter.FixedPagerAdapter;
import static cn.bproject.neteasynews.R.id.tab_layout;

public class CommentFragment extends BaseFragment {

    private final String TAG = CommentFragment.class.getSimpleName();

    private TableLayout mtableLayout;
    private ViewPager mCommentViewpager;
    private View mView;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<BaseFragment> fragments;
    private static  List<ProjectChannelBean> commentBeanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tablayout_pager, container, false);
        return mView;
    }

    public void initView() {
        mtableLayout = (TableLayout) mView.findViewById(tab_layout);
        mCommentViewpager = (ViewPager) mView.findViewById(R.id.news_viewpager);


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
}
