package com.luncode.moeplug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luncode.moeplug.Util.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.sendrequestHttp("https://v1.hitokoto.cn/?c=b",new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String responseData=response.body().string();
                    parseJSONWithGSON(responseData);
                    //Log.d("json",responseData);
            }
        });
    }
    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        HitokotoModel model = gson.fromJson(jsonData,HitokotoModel.class);
        Log.d("jsonex","ID:"+model.getId()
                +"\tTEXT:"
                +model.getHitokoto()
                +"\tto:"+model.getFrom()
                +"\t"+model.getCreator());
    }
}
