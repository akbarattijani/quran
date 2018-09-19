package com.quran.study.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quran.study.R;
import com.quran.study.adapter.DynamicAdapter;
import com.quran.study.datastructure.TestResult;
import com.quran.study.global.VariableConstant;
import com.quran.study.listener.DynamicListener;
import com.quran.study.util.AudioRecorder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestResultActivity extends BaseActivity implements DynamicListener {
    private RecyclerView rvData;

    private List<TestResult> data;
    private DynamicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setElevation(8f);

        data = new ArrayList<>();
        TestResult testResultAlFatihah = new TestResult()
                .setId("0")
                .setNameSurah("Al-Fatihah")
                .setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
                .setScor(75)
                .setRate("B");

        TestResult testResultAlIkhlas = new TestResult()
                .setId("1")
                .setNameSurah("Al-Ikhlas")
                .setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
                .setScor(89)
                .setRate("A");

        TestResult testResultAlFalaq = new TestResult()
                .setId("2")
                .setNameSurah("Al-Falaq")
                .setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
                .setScor(68)
                .setRate("C");

        data.add(testResultAlFatihah);
        data.add(testResultAlIkhlas);
        data.add(testResultAlFalaq);

        rvData = (RecyclerView) findViewById(R.id.rvData);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DynamicAdapter(this, data.size(), this, R.layout.test_attribute_layout);
        rvData.setAdapter(adapter);
    }

    @Override
    public void onBindView(DynamicAdapter.DynamicViewHolder holder, int position, boolean showNextImage, int id) {
        TextView tvId = (TextView) holder.views.get(0);
        TextView tvSurah = (TextView) holder.views.get(1);
        TextView tvDate = (TextView) holder.views.get(2);
        TextView tvScor = (TextView) holder.views.get(3);
        TextView tvRate = (TextView) holder.views.get(4);
        LinearLayout rlItemList = (LinearLayout) holder.views.get(5);
        final ImageView ivPlay = (ImageView) holder.views.get(6);

        final TestResult result = data.get(position);
        tvId.setText(result.getId());
        tvSurah.setText(result.getNameSurah());
        tvDate.setText(result.getDate());
        tvScor.setText(String.valueOf(result.getScor()));
        tvRate.setText(result.getRate());

        String rate = tvRate.getText().toString();
        if (rate.equals("A")) {
            tvRate.setTextColor(getResources().getColor(R.color.dark_blue));
            tvScor.setTextColor(getResources().getColor(R.color.dark_blue));
        } else if (rate.equals("B")) {
            tvRate.setTextColor(getResources().getColor(R.color.green));
            tvScor.setTextColor(getResources().getColor(R.color.green));
        } else if (rate.equals("C")) {
            tvRate.setTextColor(getResources().getColor(R.color.colorscanSeparator));
            tvScor.setTextColor(getResources().getColor(R.color.colorscanSeparator));
        } else if (rate.equals("D")) {
            tvRate.setTextColor(getResources().getColor(R.color.colorToolbarRed));
            tvScor.setTextColor(getResources().getColor(R.color.colorToolbarRed));
        }

        rlItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestResultActivity.this, "Akan menampilkan detil hasil, seperti rekaman perbaikan cara baca, tips, level, dll", Toast.LENGTH_LONG).show();
            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioRecorder audioRecorder = VariableConstant.getInstance().getAudioConstant().get(result.getId());

                if (audioRecorder != null) {
                    audioRecorder.startPlaying();
                } else {
                    Toast.makeText(TestResultActivity.this, "Record is not exists", Toast.LENGTH_LONG). show();
                }
            }
        });
    }

    @Override
    public List<View> onViewHolder(View view) {
        List<View> views = new ArrayList<>();
        views.add((TextView) view.findViewById(R.id.tvId));
        views.add((TextView) view.findViewById(R.id.tvSurah));
        views.add((TextView) view.findViewById(R.id.tvDate));
        views.add((TextView) view.findViewById(R.id.tvScor));
        views.add((TextView) view.findViewById(R.id.tvRate));
        views.add((LinearLayout) view.findViewById(R.id.rlItemList));
        views.add((ImageView) view.findViewById(R.id.ivPlay));

        return views;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
