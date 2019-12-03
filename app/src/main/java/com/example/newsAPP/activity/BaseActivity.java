package com.example.newsAPP.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Administrator on 2017/2/17.
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
