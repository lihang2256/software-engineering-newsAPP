package com.example.newsAPP.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.adapter.FansListAdapter.ContentsDeleteListener;
import com.example.newsAPP.adapter.FansListAdapter;
import com.example.newsAPP.bean.FansBean;
import com.example.newsAPP.common.DefineView;

public class FansListActivity extends BaseActivity implements ContentsDeleteListener,OnClickListener, DefineView {
    private  ListView myLv;
    private  Button myDeleteBtn;
    private  FansListAdapter myAdapter;
    private  List<FansBean.DataBean> myContentsList = new ArrayList<>();
    private  String userID ;
    private String fansID;


    private String[] myContentsArray;
    private List<FansBean.DataBean> mySelectedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans_list);
        userID = SharedPreferenceUtils.getInstance().getString(this,"USERID",null);

        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        myLv = (ListView) this.findViewById(R.id.my_lv2);
        myDeleteBtn = (Button) this.findViewById(R.id.my_delete_btn2);
        myDeleteBtn.setOnClickListener(this);
        initToolbar();
    }

    @Override
    public void initValidata() {
        new FansAsyncTask().execute();
    }

    class FansAsyncTask extends AsyncTask<String,Integer,ArrayList<FansBean.DataBean>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<FansBean.DataBean> doInBackground(String... strings) {

            ArrayList<FansBean.DataBean> list = new HttpUtils().getFans(userID);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<FansBean.DataBean>list) {
            super.onPostExecute(list);
            myContentsList = list;
            bindData();
        }
    }

//   删除的接口调用
//    class unFansAsyncTask extends AsyncTask<String,Integer,ArrayList<FansBean.DataBean>> {
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//        }
//
//        @Override
//        protected ArrayList<FansBean.DataBean> doInBackground(String... strings) {
//
//            ArrayList<FansBean.DataBean> list = new HttpUtils().unFans(userID, fansID);
//
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<FansBean.DataBean> list) {
//            super.onPostExecute(list);
//            mySelectedList = list;
//            bindData();
//        }
//    }
    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        if(myContentsList!=null) {
            myAdapter = new FansListAdapter(this, myContentsList, this);
            myLv.setAdapter(myAdapter);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * 根据isChecked,给选择的List中添加或删除数据
     * (non-Javadoc)
     * @see com.example.slideandselectdeletedemo.MyAdapter.ContentsDeleteListener#contentsDeleteSelect(int, boolean)
     */
    @Override
    public void contentsDeleteSelect(int position,boolean isChecked) {
        if(isChecked){
            mySelectedList.add(myContentsList.get(position));
        }else{
            mySelectedList.remove(myContentsList.get(position));
        }
    }

    /*
     * 删除指定位置的数据
     * (non-Javadoc)
     * @see com.example.slideandselectdeletedemo.MyAdapter.ContentsDeleteListener#contentDelete(int)
     */
    @Override
    public void contentDelete(int position) {
        // TODO Auto-generated method stub
        myContentsList.remove(position);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.my_delete_btn2:
                myContentsList.removeAll(mySelectedList);
                myAdapter.updateView(myContentsList);

                //判断 删除的ID
//                if(mySelectedList!=null) {
//                    for (int i = 0; i < mySelectedList.size(); i++) {
//                        fansID = mySelectedList.get(i).getID();
//                        new unFansAsyncTask().execute();
//                    }
//                }
                break;
        }
    }
    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("粉丝");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }
}



