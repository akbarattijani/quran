package com.quran.study.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quran.study.R;
import com.quran.study.adapter.SwipeStackAdapter;
import com.quran.study.listener.SwipeListener;
import com.quran.study.util.OpacityUtil;

import link.fls.SwipeStack;

public class TestActivity extends AppCompatActivity implements SwipeListener {
    private LinearLayout footer;
    private SwipeStack swipeAyat;
    private SwipeStackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        footer = (LinearLayout) findViewById(R.id.footer);
        footer.setAnimation(new OpacityUtil().set(0.5F, 0.5F));

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
}
