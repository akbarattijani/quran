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

import java.util.List;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class SwipeStackAdapter extends BaseAdapter {

    private List<String> mData;
    private Context context;

    public SwipeStackAdapter(Context context, List<String> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.loading_layout, parent, false);
            AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
            alpha.setDuration(0); // Make animation instant
            alpha.setFillAfter(true); // Tell it to persist after the animation ends
// And then on your layout
            convertView.startAnimation(alpha);
        }

        TextView textViewCard = (TextView) convertView.findViewById(R.id.tvHeader);
        textViewCard.setText(mData.get(position));

        return convertView;
    }
}
