package com.example.newsAPP.http;
import java.io.IOException;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Gson gson = new Gson();
                    String json = gson.toJson(object);
                    responseData = post("http://47.100.79.111:8080/" + route,json);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
}
