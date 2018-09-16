package com.quran.study.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.quran.study.R;
/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class StartupActivity extends BaseActivity {
    private String [] permissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

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

        if (!hasPermissions(permissions)) {
            ActivityCompat.requestPermissions(this, permissions, 0);
        } else {
            Intent intent = new Intent(this, LauncherActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean hasPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, LauncherActivity.class);
                    startActivity(intent);
                }

                finish();
                return;
        }
    }
}
