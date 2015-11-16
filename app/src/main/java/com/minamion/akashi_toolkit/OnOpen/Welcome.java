package com.minamion.akashi_toolkit.OnOpen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.minamion.akashi_toolkit.Main.Main;
import com.minamion.akashi_toolkit.R;

import pl.tajchert.sample.DotsTextView;

/**
 * Created by Minamion on 2015/10/19.
 */
public class Welcome extends AppCompatActivity {
    private DotsTextView dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dots = (DotsTextView) findViewById(R.id.dots);
        loadingStart();
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
