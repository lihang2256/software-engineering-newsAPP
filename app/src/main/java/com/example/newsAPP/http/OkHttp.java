package com.example.newsAPP.http;
import java.io.IOException;

import com.example.newsAPP.common.DatabaseApi;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String responseData;
    OkHttpClient client = new OkHttpClient();

    public String sendPost(final Object object, final String route){
        try{
            Gson gson = new Gson();
            String json = gson.toJson(object);
            responseData = post(DatabaseApi.host + route,json);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public String sendGet(final String route){
        try{
            responseData = get(DatabaseApi.host1 + route);
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
