package com.example.dansesshou.opengldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Demo2.StarSurface;

public class Demo2Activity extends AppCompatActivity {
    private StarSurface mStarSurface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_demo2);
        mStarSurface=new StarSurface(this);
        mStarSurface.requestFocus();
        mStarSurface.setFocusableInTouchMode(true);
        setContentView(mStarSurface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStarSurface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStarSurface.onPause();
    }
}
