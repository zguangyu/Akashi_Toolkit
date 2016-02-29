package com.minamion.akashi_toolkit.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xml.sax.XMLReader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Pattern;
/**
 * Created by Minamion on 2015/11/9.
 */


/**
 * Created by 灿斌 on 10/10/2015.
 */
public class JSONUtil {


    /**
     * 重写未知标签Handle
     */

    static Html.TagHandler tagHandler = new Html.TagHandler() {

        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if ("hello".equals(tag) && opening) {
                output.append("hello tag filter");
            }
        }

    };
    static Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight());
            return drawable;
        }
    };
    static Thread runnable = new Thread(new Runnable() {
        @Override
        public void run() {
            // TODO: http request.
            Message msg = new Message();
            Bundle data = new Bundle();


            try {
                readParse("http://t.kcwiki.moe/?json=1&count=30");
            } catch (Exception e) {
                e.printStackTrace();
                page1.mHandler.sendEmptyMessage(3); //连接异常
            }
            Bitmap bmp = null;
            try {
                URL myurl = new URL("http://static.kcwiki.moe/KanColleStaffAvatar.png");
                // 获得连接
                HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
                conn.setConnectTimeout(6000);//设置超时
                conn.setDoInput(true);
                conn.setUseCaches(false);//不缓存
                conn.connect();
                InputStream is = conn.getInputStream();//获得图片的数据流
                bmp = BitmapFactory.decodeStream(is);
                is.close();
                page1.mHandler.sendEmptyMessage(2);
            } catch (Exception e) {
                e.printStackTrace();
                page1.mHandler.sendEmptyMessage(3);
            }
            try {
                String img_url = "";
                String txt = "<a href='http://t.kcwiki.moe/wp-content/uploads/2016/02/untitled.png'>";

                String re1 = ".*?";    // Non-greedy match on filler
                String re2 = "(http)";    // Word 1
                String re3 = "(h)";    // Any Single Character 1
                String re4 = ".*?";    // Non-greedy match on filler
                String re5 = "(:)";    // Any Single Character 2
                String re6 = "(\\/)";    // Any Single Character 3
                String re7 = "(\\/)";    // Any Single Character 4
                String re8 = ".*?";    // Non-greedy match on filler
                String re9 = "\\.";    // Uninteresting: c
                String re10 = ".*?";    // Non-greedy match on filler
                String re11 = "\\.";    // Uninteresting: c
                String re12 = ".*?";    // Non-greedy match on filler
                String re13 = "(\\.)";    // Any Single Character 5
                String re14 = "(png)";    // Word 2

                JSONTokener jsonTokener = new JSONTokener(page1.getJSON);
                JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();

                //获取一个json数组
                JSONArray array = jsonObject.getJSONArray("posts");
                for(int i=0;i<30;i++){
                    JSONTokener jsonTokener2 = new JSONTokener(array.getString(i));
                    JSONObject jsonObject1 = (JSONObject) jsonTokener2.nextValue();
                    HashMap<String, Object> map = new HashMap<String, Object>();
//                    Log.e("json数组内容HTML",jsonObject1.getString("content"));

                    Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10 + re11 + re12 + re13 + re14, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//                    Matcher m = p.matcher(jsonObject1.getString("txt"));
//                    if (m.find())
//                    {
//                        String word1=m.group(1);
//                        String c1=m.group(2);
//                        String c2=m.group(3);
//                        String c3=m.group(4);
//                        String c4=m.group(5);
//                        String c5=m.group(6);
//                        String word2=m.group(7);
//                         img_url=("("+word1.toString()+")"+"("+c1.toString()+")"+"("+c2.toString()+")"+"("+c3.toString()+")"+"("+c4.toString()+")"+"("+c5.toString()+")"+"("+word2.toString()+")"+"\n");
//                    }
                    Log.e("图片地址获取", img_url);

                    Spanned content = Html.fromHtml(jsonObject1.getString("content"), imgGetter, null);
                    map.put("ItemTitle", content);
                    map.put("Date", jsonObject1.getString("date"));
                    map.put("ID", i);
                    map.put("Img", bmp);
                    map.put("Img2", null);
                    page1.listItem.add(map);
                    Log.e("json数组", String.valueOf(map));

                }
                page1.mHandler.sendEmptyMessage(1);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                page1.mHandler.sendEmptyMessage(3);

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
        page1.getJSON = new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
        return page1.getJSON;
    }
}