package com.example.newsAPP.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.common.DefineView;

public class InformationActivity extends BaseActivity implements DefineView {

    private RelativeLayout oldPassRL, newPassRL, confirmRL;
    private EditText nameET, oldPassET, newPassET, confirmET;
    private TextView idTV;
    private Button namebtn, oldPassbtn, newPassbtn;
    private String userId, userName, text, ttext;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("个人信息");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_information);
        initToolbar();
        mContext = this;
        idTV = findViewById(R.id.info_id_tv);
        namebtn = findViewById(R.id.info_name_btn);
        oldPassbtn = findViewById(R.id.info_old_pass_btn);
        newPassbtn = findViewById(R.id.info_new_pass_btn);
        nameET = findViewById(R.id.info_name_et);
        oldPassET = findViewById(R.id.info_old_pass_et);
        newPassET = findViewById(R.id.info_new_pass_et);
        confirmET = findViewById(R.id.info_confirm_new_pass_et);
        oldPassRL = findViewById(R.id.confirm_old_pass_rl);
        newPassRL = findViewById(R.id.input_new_pass_rl);
        confirmRL = findViewById(R.id.confirm_new_pass_rl);
        userId = SharedPreferenceUtils.getInstance().getString(this,"USERID",null);
        userName = SharedPreferenceUtils.getInstance().getString(this,"NICKNAME",null);
        oldPassRL.setVisibility(View.VISIBLE);
        newPassRL.setVisibility(View.GONE);
        confirmRL.setVisibility(View.GONE);
        if (userId != null){
            idTV.setText("用户ID： " + userId);
        }
        if (userName != null){
            nameET.setText(userName);
        }
    }

    @Override
    public void initValidata() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initListener() {
        namebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = nameET.getText().toString();
                if (text.equals("")){
                    Toast.makeText(InformationActivity.this,"请输入昵称",Toast.LENGTH_SHORT).show();
                }
                else {
                    new ModifyName().execute(userId,text);
                }
            }
        });
        oldPassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = oldPassET.getText().toString();
                if (text.equals("")){
                    Toast.makeText(InformationActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }
                else {
                    new OldPasswordAsyncTask().execute(userName,text);
                }
            }
        });
        newPassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = newPassET.getText().toString();
                ttext = confirmET.getText().toString();
                if (text.equals("")||ttext.equals("")) {
                    Toast.makeText(InformationActivity.this,"请输入完整",Toast.LENGTH_SHORT).show();
                }
                else if(text.equals(ttext)){
                    new ModifyPasswordAsyncTask().execute(userId,text);
                }
                else {
                    Toast.makeText(InformationActivity.this,"两次输入不同",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void bindData() {

    }

    class OldPasswordAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return new HttpUtils().login(strings[0],strings[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals(userId)) {
                oldPassRL.setVisibility(View.GONE);
                newPassRL.setVisibility(View.VISIBLE);
                confirmRL.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(InformationActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class ModifyPasswordAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return new HttpUtils().modifyPassword(strings[0],strings[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("success")) {
                Toast.makeText(InformationActivity.this,"修改成功，请重新登录",Toast.LENGTH_LONG).show();
                SharedPreferenceUtils.getInstance().setString(mContext,"USERID",null);
                SharedPreferenceUtils.getInstance().setString(mContext,"NICKNAME",null);
                Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(InformationActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class ModifyName extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return new HttpUtils().login(strings[0],strings[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals(userId)) {
                oldPassRL.setVisibility(View.GONE);
                newPassRL.setVisibility(View.VISIBLE);
                confirmRL.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(InformationActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
