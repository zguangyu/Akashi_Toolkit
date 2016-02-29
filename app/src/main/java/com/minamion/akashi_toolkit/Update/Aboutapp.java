package com.minamion.akashi_toolkit.Update;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.minamion.akashi_toolkit.R;

/**
 * Created by Minamion on 2015/11/14.
 */
public class Aboutapp extends AppCompatActivity {
    String localver;
    String serverver;
    String changelog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutapp);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "联系作者？", Snackbar.LENGTH_LONG)
                        .setAction("点我~", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent data=new Intent(Intent.ACTION_SENDTO);
                                data.setData(Uri.parse("mailto:minamion@outlook.com"));
                                data.putExtra(Intent.EXTRA_SUBJECT, "这里是标题~");
                                data.putExtra(Intent.EXTRA_TEXT, "这里是内容~");
                                startActivity(data);
                            }}).show();
            }
        });
        UpdateManager manager = new UpdateManager(Aboutapp.this);
        // 检查更新
        localver = manager.checkver();
        fillTextView(R.id.local_v, "当前版本号:" + localver);
        Log.e("获取", "++++++版本号++++++ " + manager.checkver() + "++++++");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button kcwiki = (Button) findViewById(R.id.kcwiki);
        kcwiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://zh.kcwiki.moe/wiki/%E8%88%B0%E5%A8%98%E7%99%BE%E7%A7%91");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);
            }
        });
        Button donate = (Button) findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://qr.alipay.com/apr9umuqfbxkxnsw52");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textview1 = (TextView) findViewById(R.id.about);
        textview1.setMovementMethod(LinkMovementMethod.getInstance());
        final CircularProgressButton circularButton03 = (CircularProgressButton) findViewById(R.id.btnWithText);
        circularButton03.setIndeterminateProgressMode(true);
        circularButton03.setProgress(0);
        circularButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final CircularProgressButton btn = (CircularProgressButton) v;
                int progress = btn.getProgress();
                UpdateManager manager = new UpdateManager(Aboutapp.this);
                // 检查更新
                final int a = manager.checkUpdate_onbtn();
                if (progress == 0) { // 初始时progress = 0
                    btn.setProgress(50); // 点击后开始不精准进度，不精准进度的进度值一直为50
                    final UpdateManager finalManager = manager;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            UpdateManager manager = new UpdateManager(Aboutapp.this);
                            // 检查更新
                            serverver = manager.checkserver_ver();
                            changelog = manager.getchangelog();
                            fillTextView(R.id.server_v, "最新版本号:" + serverver);
                            fillTextView(R.id.changelog, "更新内容:\n" + changelog);
                            Log.e("获取", "++++++版本号++++++ " + finalManager.checkserver_ver() + "++++++");
                            if (a == 0) {
                                btn.setProgress(100); // 如果不是初始状态，那么就回到初始状态
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                    }
                                }, 1000);//3秒 加载动画
                            } else {
                                btn.setProgress(-1); // 如果不是初始状态，那么就回到初始状态
                                Snackbar.make(v, "有可用更新，点我下载", Snackbar.LENGTH_LONG)
                                        .setAction("~", null).show();
                            }
                        }
                    }, 1000);//3秒 加载动画
                } else if (progress == 100) { // 如果当前进度为100，即完成状态，那么重新回到未完成的状态
                    Snackbar.make(v, "已是最新版本", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (progress == -1) { // 如果当前进度为100，即完成状态，那么重新回到未完成的状态
                    btn.setProgress(-1); // 如果不是初始状态，那么就回到初始状态
                    manager = new UpdateManager(Aboutapp.this);
                    // 检查更新
                    manager.showDownloadDialog();
                    Snackbar.make(v, "有可用更新", Snackbar.LENGTH_LONG)
                            .setAction("~", null).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillTextView (int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);
    }
}
