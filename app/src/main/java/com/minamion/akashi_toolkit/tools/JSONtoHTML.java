package com.minamion.akashi_toolkit.tools;

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
/**
 * Created by Minamion on 2015/11/9.
 */


/**
 * Created by 灿斌 on 10/10/2015.
 */
public class JSONtoHTML {

    static Thread  runnable2  = new Thread(new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            Message msg = new Message();
            Bundle data = new Bundle();
            try {
                readParse(publictings.url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONTokener jsonTokener = new JSONTokener(publictings.getJSON);
                JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();

                JSONObject jsonObject1 = jsonObject.getJSONObject("parse");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("text");
                //获取一个json数组
                publictings.string =jsonObject2.getString("*");
                Log.e("json数组", jsonObject2.getString("*"));
                page5.gx.setText(Html.fromHtml(publictings.string, publictings.imgGetter, publictings.tagHandler));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



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
        publictings.getJSON = new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
        return publictings.getJSON;
    }
}