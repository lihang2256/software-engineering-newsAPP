package com.example.newsAPP.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newsAPP.R;

public class LoginActivity extends AppCompatActivity {

    TextView signin,signup,signin_signup_txt,forgot_password;
    Button signin_signup_btn;
    EditText nickname, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = (TextView) findViewById(R.id.signin);
        signup = (TextView) findViewById(R.id.signup);
        signin_signup_txt = (TextView) findViewById(R.id.signin_signup_txt);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        signin_signup_btn = (Button) findViewById(R.id.signin_signup_btn);
        nickname = (EditText) findViewById(R.id.nickname);
        password = (EditText) findViewById(R.id.password);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin.setTextColor(Color.parseColor("#FFFFFF"));
                signin.setBackgroundColor(Color.parseColor("#1da8fe"));
                signup.setTextColor(Color.parseColor("#FF4081"));
                signup.setBackgroundResource(R.drawable.bordershape);
                signin_signup_txt.setText("Sign In");
                signin_signup_btn.setText("Sign In");
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
                signin_signup_txt.setText("Sign Up");
                signin_signup_btn.setText("Sign Up");
                forgot_password.setVisibility(View.INVISIBLE);
                //add something
            }
        });
    }
}