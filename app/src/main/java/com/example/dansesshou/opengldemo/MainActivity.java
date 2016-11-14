package com.example.dansesshou.opengldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        btn1= (Button) findViewById(R.id.btn_1);
        btn2= (Button) findViewById(R.id.btn_2);
        btn3= (Button) findViewById(R.id.btn_3);
        btn4= (Button) findViewById(R.id.btn_4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                startActivity(new Intent(this,Demo1Activity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(this,Demo2Activity.class));
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
        }
    }
}
