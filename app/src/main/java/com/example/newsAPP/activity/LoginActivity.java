package com.example.newsAPP.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newsAPP.R;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.common.DefineView;

public class LoginActivity extends BaseActivity implements DefineView {

    TextView signin,signup,signin_signup_txt,forgot_password;
    Button button;
    EditText nickname, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferenceUtils.getInstance().put(this,"USERID",20);
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

    @Override
    public void initListener() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin.setTextColor(Color.parseColor("#FFFFFF"));
                signin.setBackgroundColor(Color.parseColor("#1da8fe"));
                signup.setTextColor(Color.parseColor("#FF4081"));
                signup.setBackgroundResource(R.drawable.bordershape);
                signin_signup_txt.setText("登录");
                button.setText("登录");
                forgot_password.setVisibility(View.VISIBLE);
                //add something
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup.setTextColor(Color.parseColor("#FFFFFF"));
                signup.setBackgroundColor(Color.parseColor("#1da8fe"));
                signin.setTextColor(Color.parseColor("#FF4081"));
                signin.setBackgroundResource(R.drawable.bordershape);
                signin_signup_txt.setText("注册");
                button.setText("注册");
                forgot_password.setVisibility(View.INVISIBLE);
                //add something
            }
        });
    }

    @Override
    public void bindData() {

    }
}