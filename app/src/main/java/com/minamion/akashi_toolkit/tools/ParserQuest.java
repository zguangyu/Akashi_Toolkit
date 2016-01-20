package com.minamion.akashi_toolkit.tools;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Minamion on 2016/1/15.
 */
public class ParserQuest {

    //采用XmlPullParser来解析XML文件
    public static List<Quest> getQuest(InputStream inStream) throws Throwable
    {
        List<Quest> Quest = null;
        Quest mQuest = null;

        //========创建XmlPullParser,有两种方式=======
        //方式一:使用工厂类XmlPullParserFactory
        XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullFactory.newPullParser();
        //方式二:使用Android提供的实用工具类android.util.Xml
        //XmlPullParser parser = Xml.newPullParser();

        //解析文件输入流
        parser.setInput(inStream, "UTF-8");
        //产生第一个事件
        int eventType = parser.getEventType();
        Log.e("XML解析", "解析事件启动");
        //只要不是文档结束事件，就一直循环
        while(eventType!=XmlPullParser.END_DOCUMENT)
        {
            switch (eventType)
            {
                //触发开始文档事件
                case XmlPullParser.START_DOCUMENT:
                    Log.e("XML解析","开始解析文档");
                    break;
                //触发开始元素事件
                case XmlPullParser.START_TAG:
                    //获取解析器当前指向的元素的名称
                    String name = parser.getName();
                    if("item".equals(name))
                    {
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("index", parser.getAttributeValue(0));
                        map.put("type", parser.getAttributeValue(1));
                        map.put("period", parser.getAttributeValue(2));
                        map.put("title", parser.getAttributeValue(3));
                        map.put("content", parser.getAttributeValue(4));
                        map.put("awardRanLiao", parser.getAttributeValue(5));
                        map.put("awardDanYao", parser.getAttributeValue(6));
                        map.put("awardGang", parser.getAttributeValue(7));
                        map.put("awardLv", parser.getAttributeValue(8));
                        map.put("awardOther", parser.getAttributeValue(9));
                        map.put("unlockIndex", parser.getAttributeValue(10));



                        Log.e("XML解析", map.toString());
                        switch (Integer.parseInt(parser.getAttributeValue(1))){
                            case 1:page4.listItem1.add(map);break;//編成
                            case 2:page4.listItem2.add(map);break;//出擊
                            case 3:page4.listItem3.add(map);break;//演習
                            case 4:page4.listItem4.add(map);break;//遠征
                            case 5:page4.listItem5.add(map);break;//補給/入渠
                            case 6:page4.listItem6.add(map);break;//工廠
                            case 7:page4.listItem7.add(map);break;//改裝
                        }



                    }
                    break;
                //触发结束元素事件
                case XmlPullParser.END_DOCUMENT:
                    //
                    if("student".equals(parser.getName()))
                    {
                        Log.e("XML解析","文档解析结束");
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }
        return Quest;
    }


}
