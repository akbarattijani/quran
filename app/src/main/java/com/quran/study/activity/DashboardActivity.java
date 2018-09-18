package com.quran.study.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.quran.study.R;
import com.quran.study.util.OpacityUtil;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class DashboardActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout component;
    private LinearLayout llTest;
    private LinearLayout llLive;
    private LinearLayout llResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        component = (LinearLayout) findViewById(R.id.component);
        component.setAnimation(new OpacityUtil().set(0.5F, 0.5F));

        llTest = (LinearLayout) findViewById(R.id.llTest);
        llLive = (LinearLayout) findViewById(R.id.llLive);
        llResult = (LinearLayout) findViewById(R.id.llResult);

        llTest.setOnClickListener(this);
        llLive.setOnClickListener(this);
        llResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == llTest) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else if (v == llLive) {
            Toast.makeText(this, "Coming Soon...", Toast.LENGTH_LONG).show();
        } else if (v == llResult) {
            Intent intent = new Intent(this, TestResultActivity.class);
            startActivity(intent);
        }
    }
}
