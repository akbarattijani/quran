package com.quran.study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.quran.study.listener.DynamicListener;

import java.util.List;

/**
 * @author AKBAR <dicky.akbar@dwidasa.com>
 */
public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.DynamicViewHolder> {

    private Context context;
    private boolean showNextImage = false;
    private int layout = -1;
    private int size = 0;
    private DynamicListener listener2;
    private int id = -1;

    public DynamicAdapter(Context context, int size, DynamicListener listener2) {
        this.context = context;
        this.size = size;
        this.listener2 = listener2;
    }

    public DynamicAdapter(Context context, int size, DynamicListener listener2, boolean showNextImage) {
        this.context = context;
        this.size = size;
        this.listener2 = listener2;
        this.showNextImage = showNextImage;
    }

    public DynamicAdapter(Context context, int size, DynamicListener listener2, int layout) {
        this.context = context;
        this.size = size;
        this.listener2 = listener2;
        this.layout = layout;
    }

    public DynamicAdapter(Context context, int size, DynamicListener listener2, boolean showNextImage, int id) {
        this.context = context;
        this.size = size;
        this.listener2 = listener2;
        this.showNextImage = showNextImage;
        this.id = id;
    }

    public DynamicAdapter(Context context, int size, DynamicListener listener2, int layout, int id) {
        this.context = context;
        this.size = size;
        this.listener2 = listener2;
        this.layout = layout;
        this.id = id;
    }

    public void update() {
        notifyDataSetChanged();
    }

    public void update(int size) {
        this.size = size;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (layout != -1) {
            v = LayoutInflater.from(context).inflate(layout, parent, false);
        }
        return new DynamicViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DynamicViewHolder holder, final int position) {
        listener2.onBindView(holder, position, showNextImage, id);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class DynamicViewHolder extends RecyclerView.ViewHolder {
        public List<View> views;

        public DynamicViewHolder(View itemView) {
            super(itemView);
            views = listener2.onViewHolder(itemView);
        }
    }
}


