package com.example.newsAPP.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.newsAPP.Utils.HttpUtils;
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
    private String[] data;  //事先存好的菜单列表
    private ListView mListView;
    private View mView;
    private String userID;
    private String text;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userID = SharedPreferenceUtils.getInstance().getString(getActivity(),"USERID",null);
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

        //判断是否已经登陆，已经登陆则换上另外的头像并显示昵称
        if (userID != null){
            user_icon.setImageDrawable(getResources().getDrawable(R.drawable.photo));
            user_name.setText(SharedPreferenceUtils.getInstance().getString(getActivity(),"NICKNAME",null));
        }
        mListView.addHeaderView(user_view);
    }

    @Override
    public void initValidata() {

    }

    /**
     * 给每一个item绑定对应的函数，非登陆将锁定大部分功能
     */
    @Override
    public void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
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
                        //用户界面，已经登陆之后不可点击
                        if (userID == null) {
                            intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        //进入个人信息，目前没有实现
                        break;
                    case 2:
                        //进入关注
                        if (userID != null) {
                            intent = new Intent(getActivity(), FollowListActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        //进入粉丝
                        if (userID != null) {
                            intent = new Intent(getActivity(), FansListActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        //进入收藏
                        if (userID != null) {
                            intent = new Intent(getActivity(), CollectionListActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 5:
                        //发表动态
                        if (userID != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("请输入评动态（不超过100字）");
                            View vview = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
                            builder.setView(vview);
                            editText = vview.findViewById(R.id.comment_commit_content);
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    text = editText.getText().toString();
                                    if (text.equals("")){
                                        Toast.makeText(getActivity(), "不可以发表空评论", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        new ReleaseTrendAsyncTask().execute(userID,text,"1");
                                    }
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
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        //进入摇一摇
                        if (userID != null) {
                            intent = new Intent(getActivity(), ShakeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        //进入关于App
                        intent = new Intent(getActivity(), AboutActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        //退出登录
                        if (userID != null) {
                            SharedPreferenceUtils.getInstance().setString(getActivity(),"USERID",null);
                            SharedPreferenceUtils.getInstance().getString(getActivity(),"NICKNAME",null);
                            intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void bindData() {
        AboutAdapter adapter = new AboutAdapter();
        mListView.setAdapter(adapter);
    }

    /**
     * about界面listView适配器
     */
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

    /**
     * 发布动态异步
     */
    class ReleaseTrendAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return new HttpUtils().releaseTrend(strings[0],strings[1],strings[2]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("success")) {
                Toast.makeText( getActivity(), "发表成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
