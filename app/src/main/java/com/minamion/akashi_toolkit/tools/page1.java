package com.minamion.akashi_toolkit.tools;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.minamion.akashi_toolkit.Main.Main;
import com.minamion.akashi_toolkit.R;
import com.minamion.akashi_toolkit.Update.Aboutapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class page1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if(msg.what == 0)
            {

                page1.listItemAdapter.notifyDataSetChanged();
                page1.swipeRefreshLayout.setRefreshing(false);
            }
            else if(msg.what == 1)
            {


                page1.listItemAdapter.notifyDataSetChanged();
                page1.swipeRefreshLayout.setRefreshing(false);
            } else if(msg.what == 2)
            {
                listItem.clear();
            }else if(msg.what == 3)
            {
                getNullData();
            }
        }
    };

    public static String getJSON="";
    public static SwipeRefreshLayout swipeRefreshLayout;
    public static SimpleAdapter listItemAdapter;
    static ListView listview_twitter;
    public static ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Twitter转发");//JSON解析+显示
        listview_twitter=(ListView)findViewById(R.id.listview_twitter);
        listview_twitter.setDivider(null);
        tv = (TextView) findViewById(R.id.empty);
        listview_twitter.setEmptyView(tv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        listItemAdapter = new SimpleAdapter(page1.this, listItem,//数据源
                R.layout.list_item_card,//ListItem的XML实现
                //动态数组
                new String[]{"Img","ItemTitle", "Date"},
                //ImageItem的XML文件里面的两个TextView ID
                new int[]{R.id.image_1,R.id.text_1, R.id.text_2});
        listItemAdapter.setViewBinder(new MyViewBinder());
        getData();
        listItem.clear(); listview_twitter.setLayoutAnimation(getListAnim());listview_twitter.setAdapter(listItemAdapter);
        try {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                // SwipeRefreshLayout接管其包裹的ListView下拉事件。
                // 每一次对ListView的下拉动作，将触发SwipeRefreshLayout的onRefresh()。
                @Override
                public void onRefresh() {
                    Log.e("主线程", "刷新启动");
//                    swipeRefreshLayout.setRefreshing(true);

                    // 开始启动刷新...
                    // 在这儿放耗时操作的 AsyncTask线程、后台Service等代码。
                    getData();
                    // 刷新完毕.

                    // false，刷新完成，因此停止UI的刷新表现样式。

                }
            }); }
        catch (Exception e){
            e.printStackTrace();
        }


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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main) {
            Intent intent = new Intent(page1.this, Main.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_1) {
        } else if (id == R.id.nav_3) {
            Intent intent = new Intent(page1.this, page3.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_4) {
            Intent intent = new Intent(page1.this, page4.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_5) {
            Intent intent = new Intent(page1.this, page5.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_6) {
            Intent intent = new Intent(page1.this, page6.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_info) {
            Intent intent = new Intent(page1.this, Aboutapp.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getData() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });


        try {
            JSONUtil.runnable.start();
        } catch (Exception e) {
            try {
                new Thread(JSONUtil.runnable).start();
            } catch (Exception e2) {
                swipeRefreshLayout.setRefreshing(false);
                getNullData();
                e2.printStackTrace();
            }
        }
    }

    public static void getNullData() {
        Snackbar.make(swipeRefreshLayout, "似乎没有获取到东西呢~刷新再试试吧~", Snackbar.LENGTH_LONG)
                .setAction("", null).show();
    }
}
class ViewHolder
{
    public ImageView img;
    public TextView title;
    public TextView info;
}
class MyViewBinder implements SimpleAdapter.ViewBinder
{
    /**
     * view：要板顶数据的视图
     * data：要绑定到视图的数据
     * textRepresentation：一个表示所支持数据的安全的字符串，结果是data.toString()或空字符串，但不能是Null
     * 返回值：如果数据绑定到视图返回真，否则返回假
     */
    @Override
    public boolean setViewValue(View view, Object data,
                                String textRepresentation) {
        if((view instanceof ImageView)&(data instanceof Bitmap))
        {
            ImageView iv = (ImageView)view;
            Bitmap bmp = (Bitmap)data;
            iv.setImageBitmap(bmp);
            return true;
        }
        return false;
    }


}