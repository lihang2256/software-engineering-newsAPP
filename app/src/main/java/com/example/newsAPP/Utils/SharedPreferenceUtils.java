package com.example.newsAPP.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.channelmanager.ProjectChannelBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public final class SharedPreferenceUtils {

    private static final String FILE_NAME = "wink"; //文件名
    private static SharedPreferenceUtils mInstance;

    private SharedPreferenceUtils(){}

    public static SharedPreferenceUtils getInstance(){
        if(mInstance == null){
            synchronized (SharedPreferenceUtils.class){
                if(mInstance == null){
                    mInstance = new SharedPreferenceUtils();
                }
            }
        }
        return mInstance;
    }

    public void setDataList(Context context, String tag, List<ProjectChannelBean> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        System.out.println(strJson);
        setString(context,tag,strJson);
    }

    /**
     * 获取list
     * @param tag
     * @param channel 传入解析json所需要的Class对象
     * @return
     */
    public List<ProjectChannelBean> getDataList(Context context, String tag, Class<ProjectChannelBean> channel) {
        List<ProjectChannelBean> datalist = new ArrayList<>();
        String strJson = getString(context,tag,null);
        System.out.println(strJson);
        if (null == strJson) {
            return datalist;
        }
        JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
        for (final JsonElement elem : array) {
            datalist.add(new Gson().fromJson(elem, channel));
        }
        return datalist;
    }

    public boolean getBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public String getString(Context ctx, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public void setInt(Context ctx, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(Context ctx, String key, int defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public void setLong(Context ctx, String key, long value) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(Context ctx, String key, long defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }
}


