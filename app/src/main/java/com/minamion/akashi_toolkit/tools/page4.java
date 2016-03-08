package com.minamion.akashi_toolkit.tools;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.minamion.akashi_toolkit.Main.Main;
import com.minamion.akashi_toolkit.R;
import com.minamion.akashi_toolkit.Update.Aboutapp;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class page4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static String getJSON="";
    public static SwipeRefreshLayout swipeRefreshLayout;
    public static SimpleAdapter listItemAdapter;
    public static ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    static ListView quest1;
    static ListView quest2;
    static ListView quest3;
    static ListView quest4;
    static ListView quest5;
    static ListView quest6;
    static ListView quest7;
    static ArrayList<HashMap<String, Object>> listItem1 = new ArrayList<HashMap<String, Object>>();
    static ArrayList<HashMap<String, Object>> listItem2 = new ArrayList<HashMap<String, Object>>();
    static ArrayList<HashMap<String, Object>> listItem3 = new ArrayList<HashMap<String, Object>>();
    static ArrayList<HashMap<String, Object>> listItem4 = new ArrayList<HashMap<String, Object>>();
    static ArrayList<HashMap<String, Object>> listItem5 = new ArrayList<HashMap<String, Object>>();
    static ArrayList<HashMap<String, Object>> listItem6 = new ArrayList<HashMap<String, Object>>();
    static ArrayList<HashMap<String, Object>> listItem7 = new ArrayList<HashMap<String, Object>>();
    private static SimpleAdapter listItemAdapter1;
    private static SimpleAdapter listItemAdapter2;
    private static SimpleAdapter listItemAdapter3;
    private static SimpleAdapter listItemAdapter4;
    private static SimpleAdapter listItemAdapter5;
    private static SimpleAdapter listItemAdapter6;
    private static SimpleAdapter listItemAdapter7;
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    private List<Map<String, String>> data;
    private ProgressDialog pd;

    public void click(View v) {
        Snackbar.make(v, "笨蛋~~才沒有什么付费DLC呢~这些功能后续版本都会解锁的说~~", Snackbar.LENGTH_LONG)
                .setAction("~", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("任务");
        ViewPagerItemAdapter adapter = new ViewPagerItemAdapter(ViewPagerItems.with(this)
                .add("編成", R.layout.build)
                .add("出擊", R.layout.build)
                .add("演習", R.layout.build)
                .add("遠征", R.layout.build)
                .add("補給/入渠", R.layout.build)
                .add("工廠", R.layout.build)
                .add("改裝", R.layout.build)
                .create());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);


        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//
//                quest1 = (ListView) findViewById(R.id.quest1);
//                quest2 = (ListView) findViewById(R.id.quest2);
//                quest3 = (ListView) findViewById(R.id.quest3);
//                quest4 = (ListView) findViewById(R.id.quest4);
//                quest5 = (ListView) findViewById(R.id.quest5);
//                quest6 = (ListView) findViewById(R.id.quest6);
//                quest7 = (ListView) findViewById(R.id.quest7);
//                Log.e("PAGE4 任务解析", listItem1.toString());
//                Log.e("PAGE4 任务解析", listItem2.toString());
//                Log.e("PAGE4 任务解析", listItem3.toString());
//                Log.e("PAGE4 任务解析", listItem4.toString());
//                Log.e("PAGE4 任务解析", listItem5.toString());
//                Log.e("PAGE4 任务解析", listItem6.toString());
//                Log.e("PAGE4 任务解析", listItem7.toString());
//
//
//                listItemAdapter1 = new SimpleAdapter(page4.this, listItem1,//数据源
//                        R.layout.quest_card1,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//                listItemAdapter2 = new SimpleAdapter(page4.this, listItem2,//数据源
//                        R.layout.quest_card2,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//                listItemAdapter3 = new SimpleAdapter(page4.this, listItem3,//数据源
//                        R.layout.quest_card3,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//                listItemAdapter4 = new SimpleAdapter(page4.this, listItem4,//数据源
//                        R.layout.quest_card1,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//                listItemAdapter5 = new SimpleAdapter(page4.this, listItem5,//数据源
//                        R.layout.quest_card5,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//                listItemAdapter6 = new SimpleAdapter(page4.this, listItem6,//数据源
//                        R.layout.quest_card6,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//                listItemAdapter7 = new SimpleAdapter(page4.this, listItem7,//数据源
//                        R.layout.quest_card7,//ListItem的XML实现
//                        //动态数组
//                        new String[]{"title", "content", "awardRanLiao", "awardDanYao", "awardGang", "awardLv", "awardOther", "unlockIndex",},
//                        //ImageItem的XML文件里面的两个TextView ID
//                        new int[]{R.id.title, R.id.quest_text, R.id.you, R.id.dan, R.id.gang, R.id.lv, R.id.other, R.id.unlock});
//
//
//        listItemAdapter1.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter1.setViewBinder(new MyViewBinder());
//
//        listItemAdapter2.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter2.setViewBinder(new MyViewBinder());
//
//        listItemAdapter3.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter3.setViewBinder(new MyViewBinder());
//
//        listItemAdapter4.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter4.setViewBinder(new MyViewBinder());
//
//        listItemAdapter5.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter5.setViewBinder(new MyViewBinder());
//
//        listItemAdapter6.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter6.setViewBinder(new MyViewBinder());
//
//        listItemAdapter7.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                //判断是否为我们要处理的对象
//                if (view instanceof ImageView && data instanceof Bitmap) {
//                    ImageView iv = (ImageView) view;
//                    iv.setImageBitmap((Bitmap) data);
//                    return true;
//                } else
//                    return false;
//            }
//        });
//        listItemAdapter7.setViewBinder(new MyViewBinder());
//
//
//
//                quest1.setAdapter(listItemAdapter1);
//                quest2.setAdapter(listItemAdapter2);
//                quest3.setAdapter(listItemAdapter3);
//                quest4.setAdapter(listItemAdapter4);
//                quest5.setAdapter(listItemAdapter5);
//                quest6.setAdapter(listItemAdapter6);
//                quest7.setAdapter(listItemAdapter7);
//
//
//                quest1.setLayoutAnimation(getListAnim());
//                quest2.setLayoutAnimation(getListAnim());
//                quest3.setLayoutAnimation(getListAnim());
//                quest4.setLayoutAnimation(getListAnim());
//                quest5.setLayoutAnimation(getListAnim());
//                quest6.setLayoutAnimation(getListAnim());
//                quest7.setLayoutAnimation(getListAnim());
//
//



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

    void jump(Class app) {
        Intent intent = new Intent(page4.this, app);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            jump(Aboutapp.class);
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
            Intent intent = new Intent(page4.this, Main.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_1) {
            Intent intent = new Intent(page4.this, page1.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_3) {
            Intent intent = new Intent(page4.this, page3.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_4) {

        } else if (id == R.id.nav_5) {
            Intent intent = new Intent(page4.this, page5.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_6) {
            Intent intent = new Intent(page4.this, page6.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(page4.this, Aboutapp.class);
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
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

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

