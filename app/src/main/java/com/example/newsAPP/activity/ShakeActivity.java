package com.example.newsAPP.activity;

import android.app.Service;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.bean.UserBean;
import com.example.newsAPP.common.DefineView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ShakeActivity extends BaseActivity implements DefineView ,SensorEventListener{

    private SensorManager sensorManager = null;
    private boolean isShake = false;
    private UserBean shakeBean ;
    private String userID;
    private String friendID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        LinearLayout topLayout = (LinearLayout) findViewById(R.id.shake_top_layout);
        ImageView topLineIv = (ImageView) findViewById(R.id.shake_top_line);
        LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.shake_bottom_layout);
        ImageView bottomLineIv = (ImageView) findViewById(R.id.shake_bottom_line);
        topLineIv.setVisibility(View.GONE);
        bottomLineIv.setVisibility(View.GONE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        userID = SharedPreferenceUtils.getInstance().getString(this,"USERID",null);
        initView();
        initValidata();
        initListener();
        Toast.makeText(ShakeActivity.this, "每次进入只能摇出一个好友哦",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
                    .abs(values[2]) > 17) && !isShake) {
                isShake = true;
                new ShakeAsyncTask().execute(userID);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void initView() {
        initToolbar();
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShakeActivity.this);
        builder.setTitle("网络一线牵");
        friendID = shakeBean.getRandomID();
        String friendName = shakeBean.getRandomName();
        builder.setMessage(friendName);
        builder.setPositiveButton("添加关注", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                new IsFollowedAsyncTask().execute(userID, friendID);
            }
        });
        builder.setNegativeButton("取消添加", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(ShakeActivity.this, "已取消添加好友 " , Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("摇一摇");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
    }

    /**
     * 获取摇一摇的随机用户
     */
    class ShakeAsyncTask extends AsyncTask<String,Integer,UserBean>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected UserBean doInBackground(String... strings) {
            return new HttpUtils().random(strings[0]);
        }

        @Override
        protected void onPostExecute(UserBean person) {
            super.onPostExecute(person);
            shakeBean = person;
            bindData();
        }
    }

    /**
     * 是否关注 异步获取
     */
    class IsFollowedAsyncTask extends AsyncTask<String,Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return new HttpUtils().isFollow(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Boolean result ) {
            super.onPostExecute(result);
            if (result){
                Toast.makeText(ShakeActivity.this,"已关注了该用户",Toast.LENGTH_LONG).show();
            }
            else {
                new FollowAsyncTask().execute(userID,friendID);
            }

        }
    }

    /**
     * 关注  异步
     */
    class FollowAsyncTask extends AsyncTask<String,Integer,Boolean>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return new HttpUtils().follow(userID,friendID);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(ShakeActivity.this,"关注成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(ShakeActivity.this,"关注失败",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
