package com.example.memory.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.memory.R;
import com.example.memory.model.Item;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends BaseAdapter {
    private static Context mContext;
    private static final List<Item> mItems = new ArrayList<>();

    public GameAdapter(List<Item> mItem, Context ctx){
        this.mItems.clear();
        this.mItems.addAll(mItems);
        this.mContext = ctx;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View hat = inflater.inflate(R.layout.hat_layout, null);
        return hat;
    }
}
