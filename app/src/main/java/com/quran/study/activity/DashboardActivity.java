package com.quran.study.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.quran.study.R;
import com.quran.study.util.OpacityUtil;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        component = (LinearLayout) findViewById(R.id.component);
        component.setAnimation(new OpacityUtil().set(0.5F, 0.5F));
    }
}
