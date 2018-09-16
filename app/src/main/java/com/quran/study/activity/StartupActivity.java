package com.quran.study.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.quran.study.R;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.horizontal_progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        final int[] progress = {0};

        for (int i = 0; i < 10; i++) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progress[0] += 10;
                    progressBar.setProgress(progress[0]);
                }
            }, 2000);
        }

        Intent intent = new Intent(this, LauncherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
