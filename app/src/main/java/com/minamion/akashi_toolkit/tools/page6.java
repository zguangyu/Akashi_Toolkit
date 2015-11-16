package com.minamion.akashi_toolkit.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.minamion.akashi_toolkit.Main.Main;
import com.minamion.akashi_toolkit.R;
import com.minamion.akashi_toolkit.Update.Aboutapp;
import com.minamion.akashi_toolkit.Update.UpdateManager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItems;

import java.util.Timer;
import java.util.TimerTask;

public class page6 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public void click(View v){
        Snackbar.make(v, "笨蛋~~才沒有什么付费DLC呢~这些功能后续版本都会解锁的说~~", Snackbar.LENGTH_LONG)
                .setAction("~", null).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("开发&建造");
        ViewPagerItemAdapter adapter = new ViewPagerItemAdapter(ViewPagerItems.with(this)
                .add("装备开发", R.layout.build)
                .add("舰船建造", R.layout.build)
                .add("建造时间", R.layout.build)
                .create());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        UpdateManager manager = new UpdateManager(page6.this);
        // 检查更新
        String localver = manager.checkver();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if (id == R.id.main) {
            Intent intent = new Intent(page6.this, Main.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_1) {
            Intent intent = new Intent(page6.this, page1.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_3) {
            Intent intent = new Intent(page6.this, page3.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_4) {
            Intent intent = new Intent(page6.this, page4.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_5) {
            Intent intent = new Intent(page6.this, page5.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_6) {
        }else if (id == R.id.nav_info) {
            Intent intent = new Intent(page6.this, Aboutapp.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
//    private void fillTextView (int id, String text) {
//        TextView tv = (TextView) findViewById(id);
//        tv.setText(text);
//    }
}

