package com.quran.study.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quran.study.R;
import com.quran.study.adapter.SwipeStackAdapter;
import com.quran.study.listener.SwipeListener;
import com.quran.study.util.AudioRecorder;
import com.quran.study.util.OpacityUtil;

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

    private final int RC_START_RECORD = 0;
    private final int RC_STOP_RECORD = 1;
    private final int RC_START_PLAYING = 2;
    private final int RC_STOP_PLAYING = 3;
    private int REQUEST_CODE = RC_START_RECORD;

    private final String startRecord = "RECORD";
    private final String stop = "STOP";
    private final String startPlay = "PLAY";

    private AudioRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        footer = (LinearLayout) findViewById(R.id.footer);
        footer.setAnimation(new OpacityUtil().set(0.5F, 0.5F));

        rlRecord = (RelativeLayout) findViewById(R.id.rlRecord);
        rlSend = (RelativeLayout) findViewById(R.id.rlSend);
        tvRecord = (TextView) findViewById(R.id.tvRecord);

        tvRecord.setText(startRecord);
        rlRecord.setOnClickListener(this);
        rlSend.setOnClickListener(this);

        adapter = new SwipeStackAdapter(this, 3, this, R.layout.test_ayat_layout, R.layout.test_ayat_layout, R.layout.test_ayat_layout);
        SwipeStack stack = (SwipeStack) findViewById(R.id.swipeAyat);
        stack.setAdapter(adapter);
        stack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View[] onViewHolder(View container) {
        View[] views = new View[1];
        views[0] = (ImageView) container.findViewById(R.id.imgAyat);
        return views;
    }

    @Override
    public void onBindView(int position, View... container) {

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
            Toast.makeText(this, "Record has been sent", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
}
