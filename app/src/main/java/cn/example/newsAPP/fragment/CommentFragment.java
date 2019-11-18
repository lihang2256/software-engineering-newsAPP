package cn.example.newsAPP.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.channelmanager.ProjectChannelBean;

import java.util.ArrayList;
import java.util.List;

import cn.example.newsAPP.R;
import cn.example.newsAPP.Utils.CategoryDataUtils;
import cn.example.newsAPP.adapter.FixedPagerAdapter;
import cn.example.newsAPP.fragment.comment.CommentListFragment;

import static cn.example.newsAPP.R.id.tab_layout;

public class CommentFragment extends BaseFragment {

    private final String TAG = CommentFragment.class.getSimpleName();

    private TabLayout mtableLayout;
    private ViewPager mCommentViewpager;
    private View mView;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<BaseFragment> fragments;
    private static  List<ProjectChannelBean> channelBeanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tablayout_pager, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar myToolbar = initToolbar(mView, R.id.my_toolbar, R.id.toolbar_title, R.string.comment_name);
        initView();
    }

    public void initView() {
        mtableLayout = (TabLayout) mView.findViewById(tab_layout);
        mCommentViewpager = (ViewPager) mView.findViewById(R.id.news_viewpager);
        mView.findViewById(R.id.change_channel).setVisibility(View.GONE);
        channelBeanList = CategoryDataUtils.getComCategoryBeans();
        initValidata();
        initListener();
    }

    @Override
    public void initValidata() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());

        fragments = new ArrayList<BaseFragment>();
        for (int i = 0;i<channelBeanList.size();i++){
            ProjectChannelBean channelBean = channelBeanList.get(i);
            BaseFragment fragment = CommentListFragment.newInstance(channelBean.getTid(), channelBean.getColumn());
            fragments.add(fragment);
            fixedPagerAdapter.setChannelBean(channelBeanList);
            fixedPagerAdapter.setFragments(fragments);

            mtableLayout.setupWithViewPager(mCommentViewpager);
            mCommentViewpager.setAdapter(fixedPagerAdapter);
        }
    }

    @Override
    public void initListener() {
        mCommentViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void bindData() {

    }
}
