package com.minamion.akashi_toolkit.Update;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyThread implements Runnable{

    // 在run方法中完成网络耗时的操作
    @Override
    public void run() {

        ParseXmlService service = new ParseXmlService();
        try {
            String path = "http://www.minamion.com/kcwiki/index.php";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            UpdateManager.mHashMap = service.parseXml(inStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
