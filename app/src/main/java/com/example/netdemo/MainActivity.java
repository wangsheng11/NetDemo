package com.example.netdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_get)
    TextView mbtnGet;
    @BindView(R.id.btn_post)
    Button btnPost;
    @BindView(R.id.btn_form)
    Button btnForm;
    @BindView(R.id.btn_multiPart)
    Button btnMultiPart;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        //创建OkHttpClienk核心类
        OkHttpClient httpClient = new OkHttpClient();
        //----------------get请求----------
        //构建请求
//        Request request = new Request.Builder()
//                .url("https://api.github.com/search/repositories?q=language:java&page=1")
//                .get()
//                .build();
        RequestBody requestBody = RequestBody.create(null, "{\n" +
                "\"Password\":\"654321\",\n" +
                "\"UserName\":\"qjd\"\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register")
                .post(requestBody)
                .build();


        //发送请求
        //httpClient.neCall(request).execute()同步请求
        httpClient.newCall(request).enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "请求失败");
            }

            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG", "响应数据：" + "响应体" + response.body().string());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();

    }

    @OnClick({R.id.btn_post, R.id.btn_form, R.id.btn_multiPart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_post:
                OkHttpNetClient.getInstance().postRequest().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("POST","失败了");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("POST",response.body().string());
                    }
                });
                break;
            case R.id.btn_form:
                OkHttpNetClient.getInstance().formRequest().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("FORM","失败了");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("FORM",response.body().string());
                    }
                });
                break;
            case R.id.btn_multiPart:
                OkHttpNetClient.getInstance().multiPartRequest().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("multiPart","失败了");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("multiPart",response.body().string());
                    }
                });
                break;
        }
    }
}
