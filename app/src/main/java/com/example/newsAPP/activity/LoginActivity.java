package com.example.newsAPP.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.common.DefineView;

public class LoginActivity extends BaseActivity implements DefineView {

    private TextView signin,signup,signin_signup_txt,forgot_password;
    private Button button;
    private EditText nickname, password;
    private Context mContext;
    private String userName,mPassword;
    private int flag = 0;   //用来记录功能是登录还是注册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initView();
        initListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("登录/注册");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    @Override
    public void initView() {
        initToolbar();
        flag = 0;
        signin = (TextView) findViewById(R.id.signin);
        signup = (TextView) findViewById(R.id.signup);
        signin_signup_txt = (TextView) findViewById(R.id.signin_signup_txt);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        button = (Button) findViewById(R.id.signin_signup_btn);
        nickname = (EditText) findViewById(R.id.nickname);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void initValidata() {

    }

    /**
     * 绑定登录注册切换键和确定键的函数
     */
    @Override
    public void initListener() {
        //点登录变成登录
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin.setTextColor(Color.parseColor("#FFFFFF"));
                signin.setBackgroundColor(Color.parseColor("#1da8fe"));
                signup.setTextColor(Color.parseColor("#FF4081"));
                signup.setBackgroundResource(R.drawable.bordershape);
                signin_signup_txt.setText("登录");
                button.setText("登录");
                flag = 0;
                forgot_password.setVisibility(View.VISIBLE);
            }
        });
        //点注册变成注册
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup.setTextColor(Color.parseColor("#FFFFFF"));
                signup.setBackgroundColor(Color.parseColor("#1da8fe"));
                signin.setTextColor(Color.parseColor("#FF4081"));
                signin.setBackgroundResource(R.drawable.bordershape);
                signin_signup_txt.setText("注册");
                button.setText("注册");
                flag = 1;
                forgot_password.setVisibility(View.INVISIBLE);
            }
        });
        //根据登录注册选择合适的异步访问
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    if (nickname.getText().toString().equals("") || password.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        userName = nickname.getText().toString();
                        mPassword = password.getText().toString();
                        new LoginAsyncTask().execute(userName,mPassword);
                    }
                }
                else if (flag == 1) {
                    if (nickname.getText().toString().equals("") || password.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        userName = nickname.getText().toString();
                        mPassword = password.getText().toString();
                        new SignupAsyncTask().execute(userName,mPassword);
                    }
                }
            }
        });
    }

    @Override
    public void bindData() {

    }

    /**
     * 登录异步
     */
    class LoginAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = new HttpUtils().login(strings[0],strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null){
                Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferenceUtils.getInstance().setString(mContext, "USERID", result);
                SharedPreferenceUtils.getInstance().setString(mContext,"NICKNAME", userName);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    /**
     * 注册异步
     */
    class SignupAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = new HttpUtils().signup(strings[0],strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null){
                Toast.makeText(LoginActivity.this,"昵称重复",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(LoginActivity.this,"注册成功，请登录",Toast.LENGTH_LONG).show();
            }
        }
    }
}