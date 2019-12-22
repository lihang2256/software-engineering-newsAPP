package com.example.newsAPP.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsAPP.R;

import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.activity.AboutActivity;
import com.example.newsAPP.activity.CollectionListActivity;
import com.example.newsAPP.activity.FansListActivity;
import com.example.newsAPP.activity.FollowListActivity;
import com.example.newsAPP.activity.LoginActivity;
import com.example.newsAPP.activity.MainActivity;
import com.example.newsAPP.activity.ShakeActivity;

public class MineFragment extends BaseFragment{
    private final String TAG = MineFragment.class.getSimpleName();
    private String[] data;
    private AboutAdapter adapter;
    private ListView mListView;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_about, null);
        Toolbar myToolbar = initToolbar(mView, R.id.my_toolbar, R.id.toolbar_title, R.string.user_home);
        initView();
        bindData();
        initListener();
        return mView;
    }

    @Override
    public void initView() {
        data = getActivity().getResources().getStringArray(R.array.array_about_type);
        mListView = (ListView) mView.findViewById(R.id.list_item);

        View user_view = LayoutInflater.from(getActivity()).inflate(R.layout.user_view, mListView, false);
        ImageView user_icon = (ImageView) user_view.findViewById(R.id.user_icon);
        TextView user_name = (TextView) user_view.findViewById(R.id.user_name);
        if (SharedPreferenceUtils.getInstance().getString(getActivity(),"USERID",null) != null){
            user_icon.setImageDrawable(getResources().getDrawable(R.drawable.photo));
            user_name.setText("hello");
        }
        mListView.addHeaderView(user_view);
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                Uri uri;
//                1.个人信息
//                2.关注
//                3.粉丝
//                4.收藏
//                5.发表动态
//                6.摇一摇
//                7.关于App
//                8.退出登录
                switch (position) {
                    case 0:
                        //用户界面
                        if (SharedPreferenceUtils.getInstance().getString(getActivity(),"USERID",null) == null) {
                            intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        //进入个人信息
                        break;
                    case 2:
                        //进入关注
                        intent = new Intent(getActivity(), FollowListActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //进入粉丝
                        intent = new Intent(getActivity(), FansListActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        //进入收藏
                        intent = new Intent(getActivity(), CollectionListActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        //进入摇一摇
                        intent = new Intent(getActivity(), ShakeActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        //发表动态
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("请输入评动态（不超过100字）");
                        View mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
                        builder.setView(mView);
                        final EditText password = (EditText)view.findViewById(R.id.comment_commit_content);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //add something
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        });
                        builder.show();
                        break;
                    case 7:
                        //进入关于App
                        intent = new Intent(getActivity(), AboutActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        //退出登录
                        SharedPreferenceUtils.getInstance().setString(getActivity(),"USERID",null);
                        intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                }
            }
        });
    }

    @Override
    public void bindData() {
        adapter = new AboutAdapter();
        mListView.setAdapter(adapter);
    }

    private class AboutAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public String getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_about, null);
            TextView tv_about = (TextView) convertView.findViewById(R.id.tv_about);
            tv_about.setText(data[position]);
            return convertView;
        }
    }
}
