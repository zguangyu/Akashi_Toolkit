package com.minamion.akashi_toolkit.tools;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

import java.net.URL;

/**
 * Created by Minamion on 2015/11/11.
 */
public class publictings {
    static String url;
    public static String getJSON="";
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
    public static String string;
    public static String theme;
}
