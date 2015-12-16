package com.minamion.akashi_toolkit.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.minamion.akashi_toolkit.R;
import com.minamion.akashi_toolkit.SettingsActivity;
import com.minamion.akashi_toolkit.Update.Aboutapp;
import com.minamion.akashi_toolkit.tools.page1;
import com.minamion.akashi_toolkit.tools.page3;
import com.minamion.akashi_toolkit.tools.page4;
import com.minamion.akashi_toolkit.tools.page5;
import com.minamion.akashi_toolkit.tools.page6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private static SwipeRefreshLayout time_refresh_layout;
    private static SimpleAdapter listItemAdapter;
    ListView listview_update;
    static Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if(msg.what == 0)
            {
                Main.listItemAdapter.notifyDataSetChanged();
            }
            else if(msg.what == 1)
            {
                Main.listItemAdapter.notifyDataSetChanged();
                Main.time_refresh_layout.setRefreshing(false);
            } else if(msg.what == 2)
            {
                listItem.clear();
            }else if(msg.what == 3)
            {
                Main.time_refresh_layout.setRefreshing(false);
                getNullData();
            }
        }
    };
    public static void getNullData() {
        Snackbar.make(time_refresh_layout, "似乎没有获取到东西呢~刷新再试试吧~", Snackbar.LENGTH_LONG)
                .setAction("", null).show();
    }
    public static String getJSON="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                listItem.clear();
                listview_update=(ListView)findViewById(R.id.listview_update);
                listview_update.setDivider(null);
                listItemAdapter = new SimpleAdapter(Main.this, listItem,//数据源
                        R.layout.list_item_card2,//ListItem的XML实现
                        //动态数组
                        new String[]{"ItemTitle", "Text"},
                        //ImageItem的XML文件里面的两个TextView ID
                        new int[]{R.id.title_1,R.id.subtitle_1}
                );

                listview_update.setAdapter(listItemAdapter);
                listview_update.setLayoutAnimation(getListAnim());
                listview_update.setAdapter(listItemAdapter);
                getGameData();
                time_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.time_refresh_layout);
                time_refresh_layout.setColorSchemeResources(
                        android.R.color.holo_blue_bright,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_green_light);
                time_refresh_layout.post(new Runnable() {
                    @Override
                    public void run() {
                        time_refresh_layout.setRefreshing(true);
                    }
                });



                time_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                    // SwipeRefreshLayout接管其包裹的ListView下拉事件。
                    // 每一次对ListView的下拉动作，将触发SwipeRefreshLayout的onRefresh()。
                    @Override
                    public void onRefresh() {
                        Log.e("主线程", "刷新启动");
                        time_refresh_layout.setRefreshing(true);

                        // 开始启动刷新...
                        // 在这儿放耗时操作的 AsyncTask线程、后台Service等代码。
                        getGameData();
                        // 刷新完毕.

                        // false，刷新完成，因此停止UI的刷新表现样式。
                    }
                });

            }
        });

        thread.start();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private LayoutAnimationController getListAnim() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(200);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.5f);
        return controller;
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case  R.id.main:;
            case  R.id.nav_1:
                    jump(page1.class); finish();break;
            case  R.id.nav_3:
                jump(page3.class); finish();break;
            case  R.id.nav_4:
                jump(page4.class); finish();break;
            case  R.id.nav_5:
                jump(page5.class); finish();break;
            case  R.id.nav_6:
                jump(page6.class); finish();break;
            case  R.id.nav_info:
                jump(Aboutapp.class);break;
            case  R.id.nav_setting:
                jump(SettingsActivity.class);break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
void jump(Class app){
    Intent intent = new Intent(Main.this,app);
    startActivity(intent);

}
    public void getGameData() {
        try {
            //获取
//            time_refresh_layout.post(new Runnable() {
//                @Override
//                public void run() {
//                    time_refresh_layout.setRefreshing(true);
//                }
//            });
            listItem.clear();
            try {
                JSON2.runnable.start();
            } catch (Exception e) {
                new Thread(JSON2.runnable).start();
            }
        } catch (Exception e) {
            Snackbar.make(findViewById(R.id.activity_main_swipe_refresh_layout), "似乎没有获取到东西呢~刷新再试试吧~", Snackbar.LENGTH_LONG)
                    .setAction("", null).show();
        }
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
