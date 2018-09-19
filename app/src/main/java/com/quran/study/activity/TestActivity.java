package com.quran.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quran.study.R;
import com.quran.study.adapter.SwipeStackAdapter;
import com.quran.study.global.VariableConstant;
import com.quran.study.listener.SwipeListener;
import com.quran.study.util.AudioRecorder;
import com.quran.study.util.OpacityUtil;

import java.util.HashMap;
import java.util.Map;

import link.fls.SwipeStack;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class TestActivity extends BaseActivity implements SwipeListener, View.OnClickListener {
    private LinearLayout footer;
    private SwipeStack swipeAyat;
    private SwipeStackAdapter adapter;

    private RelativeLayout rlRecord;
    private RelativeLayout rlSend;
    private TextView tvRecord;

    private SwipeStack stack;

    private final int RC_START_RECORD = 0;
    private final int RC_STOP_RECORD = 1;
    private final int RC_START_PLAYING = 2;
    private final int RC_STOP_PLAYING = 3;
    private int REQUEST_CODE = RC_START_RECORD;

    private final String startRecord = "Record";
    private final String stop = "Stop";
    private final String startPlay = "Play";

    private AudioRecorder recorder;
    private Map<String, AudioRecorder> dataAudio = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setElevation(8f);

        footer = (LinearLayout) findViewById(R.id.footer);
        footer.setAnimation(new OpacityUtil().set(0.5F, 0.5F));

        rlRecord = (RelativeLayout) findViewById(R.id.rlRecord);
        rlSend = (RelativeLayout) findViewById(R.id.rlSend);
        tvRecord = (TextView) findViewById(R.id.tvRecord);

        tvRecord.setText(startRecord);
        rlRecord.setOnClickListener(this);
        rlSend.setOnClickListener(this);

        adapter = new SwipeStackAdapter(this, 3, this, R.layout.test_ayat_layout, R.layout.test_ayat_layout, R.layout.test_ayat_layout);
        stack = (SwipeStack) findViewById(R.id.swipeAyat);
        stack.setAdapter(adapter);
        stack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                settingRecording(position);
            }

            @Override
            public void onViewSwipedToRight(int position) {
                settingRecording(position);
            }

            @Override
            public void onStackEmpty() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void settingRecording(int position) {
        if (REQUEST_CODE == RC_STOP_RECORD || REQUEST_CODE == RC_START_PLAYING || REQUEST_CODE == RC_STOP_PLAYING) {
            if (recorder != null && !recorder.recordingIsNull() && !recorder.playerIsNull()) {
                AudioRecorder audioRecorder = new AudioRecorder(this,recorder.getFileName())
                        .setMediaRecorder(recorder.getMediaRecorder())
                        .setMediaPlayer(recorder.getMediaPlayer());

                dataAudio.put(String.valueOf(position), audioRecorder);
                recorder.stopRecording();
                recorder.stopPlaying();
            }

            if (recorder != null && !recorder.recordingIsNull()) {
                recorder.stopRecording();
            }

            if (recorder != null && !recorder.playerIsNull()) {
                recorder.stopPlaying();
            }

            recorder = new AudioRecorder(TestActivity.this, String.valueOf(System.currentTimeMillis()) + ".3dp");
            REQUEST_CODE = RC_START_RECORD;
            tvRecord.setText(startRecord);

            VariableConstant.getInstance().setAudioConstant(dataAudio);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View[] onViewHolder(View container) {
        View[] views = new View[4];
        views[0] = (ImageView) container.findViewById(R.id.imgAyat);
        views[1] = (TextView) container.findViewById(R.id.tvName);
        views[2] = (TextView) container.findViewById(R.id.tvSlideAyat);
        views[3] = (ImageView) container.findViewById(R.id.ivAyat);

        return views;
    }

    @Override
    public void onBindView(int position, View... container) {
        if (position == 0) {
            ((ImageView) container[0]).setImageDrawable(getResources().getDrawable(R.drawable.alfatihah));
            ((TextView) container[1]).setText("Al-Fatihah");
        } else if (position == 1) {
            ((ImageView) container[0]).setImageDrawable(getResources().getDrawable(R.drawable.alikhlas));
            ((TextView) container[1]).setText("Al-Ikhlas");
        } else if (position == 2) {
            ((ImageView) container[0]).setImageDrawable(getResources().getDrawable(R.drawable.alfalaq));
            ((TextView) container[1]).setText("Al-Falaq");
        }

        ((TextView) container[2]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stack.swipeTopViewToRight();
            }
        });

        ((ImageView) container[3]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stack.swipeTopViewToRight();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == rlRecord) {
            if (REQUEST_CODE == RC_START_RECORD) {
                recorder = new AudioRecorder(this, String.valueOf(System.currentTimeMillis()) + ".3dp");
                recorder.startRecording();
                REQUEST_CODE = RC_STOP_RECORD;
                tvRecord.setText(stop);
            } else if (REQUEST_CODE == RC_STOP_RECORD) {
                AudioRecorder audioRecorder = new AudioRecorder(this,recorder.getFileName())
                        .setMediaRecorder(recorder.getMediaRecorder())
                        .setMediaPlayer(recorder.getMediaPlayer());

                dataAudio.put(String.valueOf(stack.getCurrentPosition()), audioRecorder);
                recorder.stopRecording();
                REQUEST_CODE = RC_START_PLAYING;
                tvRecord.setText(startPlay);
            } else if (REQUEST_CODE == RC_START_PLAYING) {
                recorder.startPlaying();
                REQUEST_CODE = RC_STOP_PLAYING;
                tvRecord.setText(stop);
            } else if (REQUEST_CODE == RC_STOP_PLAYING) {
                recorder.stopPlaying();
                REQUEST_CODE = RC_START_PLAYING;
                tvRecord.setText(startPlay);
            }
        } else if (v == rlSend) {
            Intent intent = new Intent(this, TestResultActivity.class);
            startActivity(intent);
        }
    }
}
