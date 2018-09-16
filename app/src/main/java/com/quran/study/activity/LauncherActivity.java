package com.quran.study.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.quran.study.R;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llLogin;
    private LinearLayout llRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        llLogin = (LinearLayout) findViewById(R.id.llLogin);
        llRegister = (LinearLayout) findViewById(R.id.llRegister);

        llLogin.setOnClickListener(this);
        llRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BeginActivity.class);
        if (v == llLogin) {
            intent.putExtra("requestCode", BeginActivity.RC_LOGIN);
        } else if (v == llRegister) {
            intent.putExtra("requestCode", BeginActivity.RC_REGISTER);
        }
        startActivity(intent);
    }
}
