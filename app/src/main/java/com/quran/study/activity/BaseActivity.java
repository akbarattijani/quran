package com.quran.study.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.quran.study.R;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final String LINE_SEPARATOR = "\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                StringWriter stackTrace = new StringWriter();
                ex.printStackTrace(new PrintWriter(stackTrace));
                System.out.println(ex.getMessage());

                StringBuilder errorReport = new StringBuilder();
                errorReport.append("************ CAUSE OF ERROR ************\n\n");
                errorReport.append(stackTrace.toString());

                errorReport.append("\n************ DEVICE INFORMATION ***********\n");
                errorReport.append("Brand: ");
                errorReport.append(Build.BRAND);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Device: ");
                errorReport.append(Build.DEVICE);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Model: ");
                errorReport.append(Build.MODEL);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Id: ");
                errorReport.append(Build.ID);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Product: ");
                errorReport.append(Build.PRODUCT);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("\n************ FIRMWARE ************\n");
                errorReport.append("SDK: ");
                errorReport.append(Build.VERSION.SDK);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Release: ");
                errorReport.append(Build.VERSION.RELEASE);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Incremental: ");
                errorReport.append(Build.VERSION.INCREMENTAL);
                errorReport.append(LINE_SEPARATOR);

                Intent intent = new Intent(BaseActivity.this, BaseErrorActivity.class);
                intent.putExtra("error", errorReport.toString());
                startActivity(intent);
                finish();

                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDarkBlue));
        }
    }

    protected void initToolbarWIthBackButton(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     onBackPressed();
                                                 }
                                             }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
