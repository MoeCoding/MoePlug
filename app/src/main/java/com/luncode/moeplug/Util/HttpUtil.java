package com.luncode.moeplug.Util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendrequestHttp(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
