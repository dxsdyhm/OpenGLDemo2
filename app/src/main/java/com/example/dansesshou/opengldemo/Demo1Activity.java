package com.example.dansesshou.opengldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Demo1.TDView;

public class Demo1Activity extends AppCompatActivity {
    private TDView mview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_demo1);
        mview=new TDView(this);
        mview.requestFocus();
        mview.setFocusableInTouchMode(true);
        setContentView(mview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mview.onPause();
    }
}
