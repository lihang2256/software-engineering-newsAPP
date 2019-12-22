package com.example.newsAPP.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class CreateDialogUtils {
    public CreateDialogUtils(final Context context, String name){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("关注");
        builder1.setMessage("是否关注" + name);
        builder1.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder1.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder1.show();
    }
}
