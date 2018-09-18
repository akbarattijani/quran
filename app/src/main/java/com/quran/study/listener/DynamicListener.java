package com.quran.study.listener;
import android.view.View;

import com.quran.study.adapter.DynamicAdapter;

import java.util.List;

/**
 * @author AKBAR <dicky.akbar@dwidasa.com>
 */

public interface DynamicListener {
    public void onBindView(DynamicAdapter.DynamicViewHolder holder, int position, boolean showNextImage, int id);
    public List<View> onViewHolder(View view);
}
