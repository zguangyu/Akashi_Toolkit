package com.minamion.akashi_toolkit.Main;

import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Minamion on 2015/11/9.
 */

public class JSON2 {
    /**
     * 重写未知标签Handle
     */
    static Thread  runnable  = new Thread(new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            Message msg = new Message();
            Bundle data = new Bundle();

            try {
                readParse("http://zh.kcwiki.moe/api.php?action=parse&text={{%E7%BB%B4%E6%8A%A4%E5%80%92%E6%95%B0}}&format=json");
            } catch (Exception e) {
                e.printStackTrace();
                Main.mHandler.sendEmptyMessage(3);
            }
            try {
                JSONTokener jsonTokener = new JSONTokener(Main.getJSON);
                JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();

                JSONObject jsonObject1 = jsonObject.getJSONObject("parse");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("text");
                //获取一个json数组
                Log.e("json数组", jsonObject2.getString("*"));
                String result;
                try{
                    String str = jsonObject2.getString("*");
                     result = str.substring(0, str.indexOf("<br"));
                }catch  (Exception e) {
                     result = jsonObject2.getString("*");
                }
                Log.e("json字符串截取", result);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", "游戏维护通知:");
                map.put("Text",  Html.fromHtml(result+"</p>"));
                Main.listItem.add(map);
                Log.e("json数组map1显示", String.valueOf(map));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Main.mHandler.sendEmptyMessage(3);
            }


            try {
                readParse("http://zh.kcwiki.moe/api.php?action=parse&text={{%E6%B3%A8%E5%86%8C%E6%83%85%E6%8A%A5}}&format=json");
                Log.e("json", "获取");
            } catch (Exception e) {
                e.printStackTrace();
                Main.mHandler.sendEmptyMessage(3);
            }
            try {
                Log.e("json", "解析");
                JSONTokener jsonTokener = new JSONTokener(Main.getJSON);
                JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();

                JSONObject jsonObject1 = jsonObject.getJSONObject("parse");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("text");
                //获取一个json数组
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", "开放注册通知:");
                map.put("Text", Html.fromHtml(jsonObject2.getString("*")));
                Main.listItem.add(map);
                Log.e("json数组map2显示", String.valueOf(map));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Main.mHandler.sendEmptyMessage(3);
            }
            Main.mHandler.sendEmptyMessage(1);
            msg.setData(data);
        }});



    public static String readParse(String urlPath) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        Main.getJSON = new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
        return Main.getJSON;
    }

}