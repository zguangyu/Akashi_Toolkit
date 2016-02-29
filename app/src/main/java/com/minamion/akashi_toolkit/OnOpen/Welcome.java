package com.minamion.akashi_toolkit.OnOpen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.minamion.akashi_toolkit.Main.Main;
import com.minamion.akashi_toolkit.R;
import com.minamion.akashi_toolkit.Update.UpdateManager;
import com.umeng.analytics.MobclickAgent;

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
        final UpdateManager manager = new UpdateManager(Welcome.this);
        final int a = manager.checkUpdate_onbtn();
        if (a == 1) {
            //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
            AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
            //    设置Title的内容
            builder.setTitle("发现更新");
            //    设置Content来显示一个信息
            builder.setMessage("是否下载？？");
            //    设置一个PositiveButton
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    manager.showDownloadDialog();
                }
            });
            //    设置一个NegativeButton
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            //    显示出该对话框
            builder.show();

        }
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager asset = getAssets();
                try {
//                    InputStream input = asset.open("Resource/Data/zh/Quest.xml");
//                    Log.e("XML解析", "解析线程启动");
//                    List<Quest> list = ParserQuest.getQuest(input);
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
