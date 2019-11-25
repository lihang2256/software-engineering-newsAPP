package com.example.newsAPP.activity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.example.newsAPP.R;
import com.example.newsAPP.adapter.FriendListAdapter.ContentsDeleteListener;
import com.example.newsAPP.adapter.FriendListAdapter;

public class FriendListActivity extends Activity implements ContentsDeleteListener,OnClickListener{
    private  ListView myLv;
    private Button myDeleteBtn;
    private FriendListAdapter myAdapter;
    private String[] myContentsArray;
    private List<String> myContentsList = new ArrayList<String>();
    private List<String> mySelectedList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        findById();

        myContentsArray = this.getResources().getStringArray(R.array.my_contents);
        if(myContentsArray != null){
            Collections.addAll(myContentsList, myContentsArray);
        }

        myAdapter = new FriendListAdapter(this,myContentsList,this);
        myLv.setAdapter(myAdapter);
    }

    private void findById(){
        myLv = (ListView) this.findViewById(R.id.my_lv);
        myDeleteBtn = (Button) this.findViewById(R.id.my_delete_btn);

        myDeleteBtn.setOnClickListener(this);
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
            case R.id.my_delete_btn:
                myContentsList.removeAll(mySelectedList);
                myAdapter.updateView(myContentsList);
                break;
        }
    }

}


