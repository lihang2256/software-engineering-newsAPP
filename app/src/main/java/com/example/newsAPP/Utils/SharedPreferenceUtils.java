package com.example.newsAPP.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

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

    /**
     * 存入键值对
     * @param context
     * @param key
     * @param value
     */

    public void put(Context context, String key, Object value){
        //判断类型
        String type = value.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if("Integer".equals(type)){
            editor.putInt(key,(Integer)value);
        }else if ("Boolean".equals(type)){
            editor.putBoolean(key,(Boolean)value);
        }else if ("Float".equals(type)){
            editor.putFloat(key,(Float)value);
        }else if ("Long".equals(type)){
            editor.putLong(key,(Long)value);
        }else if ("String".equals(type)){
            editor.putString(key,(String) value);
        }
        editor.apply();
    }

    /**
     * 读取键的值，若无则返回默认值
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    @Nullable
    public Object get(Context context, String key, Object defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String type = defValue.getClass().getSimpleName();
        if("Integer".equals(type)){
            return sharedPreferences.getInt(key,(Integer)defValue);
        }else if ("Boolean".equals(type)){
            return sharedPreferences.getBoolean(key,(Boolean)defValue);
        }else if ("Float".equals(type)){
            return sharedPreferences.getFloat(key,(Float)defValue);
        }else if ("Long".equals(type)){
            return sharedPreferences.getLong(key,(Long)defValue);
        }else if ("String".equals(type)){
            return sharedPreferences.getString(key,(String) defValue);
        }
        return null;
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(Context context, String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        System.out.println(strJson);
        put(context,tag,strJson);
    }

    /**
     * 获取list
     * @param tag
     * @param clazz 传入解析json所需要的Class对象
     * @return
     */
    public <T> List<T> getDataList(Context context, String tag, Class<T> clazz) {
        List<T> datalist = new ArrayList<>();
        String strJson = (String) get(context,tag,null);
        System.out.println(strJson);
        if (null == strJson) {
            return datalist;
        }
        JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
        for (final JsonElement elem : array) {
            datalist.add(new Gson().fromJson(elem, clazz));
        }
        return datalist;
    }
}


