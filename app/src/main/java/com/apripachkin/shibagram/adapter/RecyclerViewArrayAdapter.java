package com.apripachkin.shibagram.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apripachkin.shibagram.viewholder.SimpleArrayAdapterViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Antony on 01.08.2016.
 */
public class RecyclerViewArrayAdapter<T> extends RecyclerView.Adapter<SimpleArrayAdapterViewHolder> {
    private List<T> objList = new ArrayList<>();
    private OnOptionClickedListener mCallback;

    public RecyclerViewArrayAdapter(List<T> data, OnOptionClickedListener callback) {
        updateData(data);
        this.mCallback = callback;
    }

    public void updateData(List<T> newData) {
        if (newData != null) {
            objList.clear();
            objList.addAll(newData);
            notifyItemRangeChanged(0, objList.size() - 1);
        }
    }

    @Override
    public SimpleArrayAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new SimpleArrayAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SimpleArrayAdapterViewHolder holder, final int position) {
        final T t = objList.get(position);
        holder.mArrayItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onOptionClicked(position);
            }
        });
        holder.setText(t.toString());
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public interface OnOptionClickedListener {
        void onOptionClicked(int position);
    }
}
