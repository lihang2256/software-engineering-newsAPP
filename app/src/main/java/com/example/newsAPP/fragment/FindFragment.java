package com.example.newsAPP.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.channelmanager.ProjectChannelBean;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.CategoryDataUtils;

import java.util.ArrayList;
import java.util.List;

import com.example.newsAPP.adapter.FixedPagerAdapter;
import com.example.newsAPP.common.DefineView;
import com.example.newsAPP.fragment.find.FindListFragment;

import static com.example.newsAPP.R.id.tab_layout;

public class FindFragment extends BaseFragment implements DefineView {
    private final String TAG = FindFragment.class.getSimpleName();

    private TabLayout mtableLayout;
    private ViewPager mFindViewpager;
    private View mView;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<BaseFragment> fragments;
    private static  List<ProjectChannelBean> channelBeanList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.tablayout_pager, container, false);
        initView();

        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar myToolbar = initToolbar(mView, R.id.my_toolbar, R.id.toolbar_title, R.string.find_home);
        initView();
    }
    public void initView() {
        mtableLayout = (TabLayout) mView.findViewById(tab_layout);
        mFindViewpager = (ViewPager) mView.findViewById(R.id.news_viewpager);
        mView.findViewById(R.id.change_channel).setVisibility(View.GONE);
        channelBeanList = CategoryDataUtils.getFindCategoryBeans();
        initValidata();
        initListener();

    }
    @Override
    public void initValidata() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());

        fragments = new ArrayList<BaseFragment>();
        for (int i = 0;i<channelBeanList.size();i++){
            String str = "";
            ProjectChannelBean channelBean = channelBeanList.get(i);
            BaseFragment fragment = FindListFragment.newInstance(channelBean.getTname());
            fragments.add(fragment);
            fixedPagerAdapter.setChannelBean(channelBeanList);
            fixedPagerAdapter.setFragments(fragments);
            mtableLayout.setupWithViewPager(mFindViewpager);
            mFindViewpager.setAdapter(fixedPagerAdapter);
        }
    }
    @Override
    public void initListener() {
        mFindViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
