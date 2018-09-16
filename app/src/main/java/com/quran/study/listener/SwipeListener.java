package com.quran.study.listener;

import android.view.View;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public interface SwipeListener {
    View[] onViewHolder(View container);
    void onBindView(final int position, View... container);
}
