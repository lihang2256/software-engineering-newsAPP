package com.example.newsAPP.http;
import java.io.IOException;

import com.example.newsAPP.common.DatabaseApi;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装okHttp访问
 */
public class OkHttp {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String responseData;
    OkHttpClient client = new OkHttpClient();

    /**
     * post访问
     * @param object ApiObject
     * @param key 关键词
     * @return json
     */
    public String sendPost(final Object object, final String key){
        try{
            Gson gson = new Gson();
            String json = gson.toJson(object);
            responseData = post(DatabaseApi.host + key,json);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    /**
     * get访问
     * @param key 关键词
     * @return json
     */
    public String sendGet(final String key){
        try{
            responseData = get(DatabaseApi.host + key);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        return responseData;
    }

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        return responseData;
    }
}
