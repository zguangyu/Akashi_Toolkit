package com.minamion.akashi_toolkit.Map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.minamion.akashi_toolkit.R;

/**
 * Created by Minamion on 2015/11/16.
 */
public class map extends AppCompatActivity {
    int map_a;
    int map_b;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        switch (map_a) {
            case 1:
                area1();break;
            case 2:
                area2();break;
            case 3:
                area3();break;
            case 4:
                area4();break;
            case 5:
                area5();break;
            case 6:
                area6();break;
        }
    }

    private void area1() {
        switch (map_b) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
    private void area2() {
        switch (map_b) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
    private void area3() {
        switch (map_b) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
    private void area4() {
        switch (map_b) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
    private void area5() {
        switch (map_b) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
    private void area6() {
        switch (map_b) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }

}
