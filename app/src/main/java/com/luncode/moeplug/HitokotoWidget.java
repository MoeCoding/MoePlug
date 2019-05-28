package com.luncode.moeplug;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luncode.moeplug.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Implementation of App Widget functionality.
 */
public class HitokotoWidget extends AppWidgetProvider {

    private static String Url="https://v1.hitokoto.cn/?c";
    static  RemoteViews views = new RemoteViews("com.luncode.moeplug", R.layout.hitokoto_widget);
    static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        //views.setTextViewText(R.id.hitokoto_id,"00001");
        runhandler(appWidgetManager,appWidgetId);

    }

    public static void runhandler(final AppWidgetManager appWidgetManager,final int appWidgetId){
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Hitokoto();
                appWidgetManager.updateAppWidget(appWidgetId,views);
                handler.postDelayed(this,20*1000);
            }
        };
        runnable.run();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            //Hitokoto();
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    private static  String[] parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        HitokotoModel model = gson.fromJson(jsonData,HitokotoModel.class);
//        Log.d("jsonhandler","ID:"+model.getId()
//                +"\tTEXT:"
//                +model.getHitokoto()
//                +"\tto:"+model.getFrom()
//                +"\tCreator:"+model.getCreator());
        //setHikoto(model.getId(),model.getHitokoto(),model.getFrom(),model.getCreator());
        String list[] = {model.getId(),model.getHitokoto(),model.getFrom(),model.getCreator()};
        return list;
    }

    private static void Hitokoto(){
        HttpUtil.sendrequestHttp(Url,new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.d("response",responseData);
                String list[]=parseJSONWithGSON(responseData);
                setHikoto(list[0],list[1],list[2],list[3]);
            }
        });
    }

    private static void setHikoto(String id, String text, String form, String Creator){
        views.setTextViewText(R.id.hitokoto_id,"ID:"+id);
        views.setTextViewText(R.id.hitokoto_text,text);
        views.setTextViewText(R.id.hitokoto_from,"TO:"+form);
        views.setTextViewText(R.id.hitokoto_Creator,"Creator:"+Creator);
    }

}

