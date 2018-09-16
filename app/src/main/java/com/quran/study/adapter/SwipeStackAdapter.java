package com.quran.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quran.study.R;
import com.quran.study.listener.SwipeListener;

import java.util.List;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class SwipeStackAdapter extends BaseAdapter {

    private List<String> mData;
    private View[] views;
    private int[] layouts;
    private int size;
    private Context context;
    private SwipeListener listener;

//    public SwipeStackAdapter(Context context, List<String> data) {
//        this.context = context;
//        this.mData = data;
//    }

    public SwipeStackAdapter(Context context, int size, SwipeListener listener, int... layouts) {
        this.context = context;
        this.layouts = layouts;
        this.size = size;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layouts[position], parent, false);
        views = listener.onViewHolder(convertView);
        listener.onBindView(position, views);

        return convertView;
    }
}
