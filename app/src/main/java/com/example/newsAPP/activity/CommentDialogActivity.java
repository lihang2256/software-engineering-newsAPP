package com.example.newsAPP.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.newsAPP.R;

public class CommentDialogActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView icon;
    private Button commit;
    private static String TAG = "CommentDialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);
        icon = (ImageView) findViewById(R.id.comment_commit_close_button);
        commit = (Button)findViewById(R.id.comment_commit_button);
        editText = (EditText)findViewById(R.id.comment_editText);
        Intent intent = getIntent();
        String mDocid = intent.getStringExtra("DOCID");
        editText.setText(mDocid);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
