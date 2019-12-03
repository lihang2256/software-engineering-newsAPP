package com.example.newsAPP.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import com.example.newsAPP.R;


/**
 * 单张可缩放的图片
 * 从NewsDetailActivity的webView中点击图片进入
 */
public class PhotoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }

        String imageUrl = getIntent().getStringExtra("image_url");
        PhotoView photoView = (PhotoView) findViewById(R.id.photoview);
        Glide.with(this).load(imageUrl).into(photoView);
    }
}
