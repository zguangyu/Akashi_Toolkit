package com.minamion.akashi_toolkit.OnOpen;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.minamion.akashi_toolkit.Main.Main;
import com.minamion.akashi_toolkit.R;
import com.minamion.akashi_toolkit.tools.ParserQuest;
import com.minamion.akashi_toolkit.tools.Quest;
import com.umeng.analytics.MobclickAgent;

import java.io.InputStream;
import java.util.List;

import pl.tajchert.sample.DotsTextView;

/**
 * Created by Minamion on 2015/10/19.
 */
public class Welcome extends AppCompatActivity {
    private DotsTextView dots;

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dots = (DotsTextView) findViewById(R.id.dots);
        loadingStart();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager asset = getAssets();
                try {
                    InputStream input = asset.open("Resource/Data/zh/Quest.xml");
                    Log.e("XML解析", "解析线程启动");
                    List<Quest> list = ParserQuest.getQuest(input);
//                    for (Quest stu : list) {
//                        Log.e("QusetInfo","Person ID: " + stu.getId() + ","
//                                + stu.getTitle() + ", " + stu.getContent() );
//                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }
        });
        thread2.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this, Main.class);
                startActivity(intent);
                finish();
            }
        }, 2000);//3秒 欢迎界面
    }
   void loadingStart(){
       dots.start();
       dots.showAndPlay();
    }
}
