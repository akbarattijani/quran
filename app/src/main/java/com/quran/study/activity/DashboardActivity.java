package com.quran.study.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.quran.study.R;
import com.quran.study.util.OpacityUtil;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout component;
    private LinearLayout llTest;
    private LinearLayout llLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        component = (LinearLayout) findViewById(R.id.component);
        component.setAnimation(new OpacityUtil().set(0.5F, 0.5F));

        llTest = (LinearLayout) findViewById(R.id.llTest);
        llLive = (LinearLayout) findViewById(R.id.llLive);

        llTest.setOnClickListener(this);
        llLive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == llTest) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else if (v == llLive) {

        }
    }
}
