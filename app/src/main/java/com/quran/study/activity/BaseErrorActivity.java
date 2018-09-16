package com.quran.study.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.quran.study.R;

public class BaseErrorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_error);
        TextView errorText = (TextView) findViewById(R.id.error_text);
        String errorMessage = getIntent().getExtras().getString("error");
        errorText.setText(errorMessage);
    }
}
