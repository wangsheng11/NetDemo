package com.example.netdemo;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/7/11.
 * 单例模式
 */

public class OkHttpNetClient {
    /**
     * 饿汉式：线程安全的，空间换时间
     * 懒汉式：线程不安全的（可以加同步锁使其线程安全）
     */

    private static OkHttpNetClient mOkHttpNetClient;
    private final OkHttpClient mClient;

    private OkHttpNetClient(){
        mClient=new OkHttpClient();
    }
    public static synchronized OkHttpNetClient getInstance(){
        if (mOkHttpNetClient==null){
            mOkHttpNetClient=new OkHttpNetClient();
        }
        return mOkHttpNetClient;
    }

    public Call postRequest(){
        RequestBody requestBody=RequestBody.create(null,"{\n" +
                "\"Password\":\"654321\",\n" +
                "\"UserName\":\"qjd\"\n" +
                "}");
        Request request=new Request.Builder()
                .url("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register")
                .post(requestBody)
                .build();
        return mClient.newCall(request);
    }

    public Call formRequest(){
        FormBody formBody=new FormBody.Builder()
                .add("username","张三")
                .add("password","123")
                .build();

        Request request=new Request.Builder()
                .post(formBody)
                .url("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register")
                .build();
        return mClient.newCall(request);
    }

    public Call getRequest(){
        Request request = new Request.Builder()
                .url("https://api.github.com/search/repositories?q=language:java&page=1")
                .get()
                .build();
        return mClient.newCall(request);
    }
    public Call multiPartRequest(){
        MultipartBody multipartBody=new MultipartBody.Builder()
                .addPart(RequestBody.create(null,"{\n" +
                        "\"name\": \"yt59856b15cf394e7b84a7d48447d16098\",\n" +
                        "\"username\": \"xc62\",\n" +
                        "\"nickname\": \"555\",\n" +
                        "\"password\": \"123456\",\n" +
                        "\"uuid\": \"0F8EC12223174657B2E842076D54C361\"\n" +
                        "}"))
                .build();

        Request request= new Request.Builder()
                .url("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=update")
                .post(multipartBody)
                .build();
          return mClient.newCall(request);
    }
}
