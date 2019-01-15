package com.example.sean.look.first.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sean.look.R;
import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.example.sean.look.first.mvp.ui.ItemHolder.ItemHolder;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean
 * @data 2019/1/15
 */
public class RecyclerViewAdapter extends DefaultAdapter<NewsInfo> {

    private List<NewsInfo> infos = new ArrayList<>();

    public RecyclerViewAdapter(List<NewsInfo> list) {
        super(list);
        this.infos = list;
    }

    @Override
    public BaseHolder<NewsInfo> getHolder(View v, int viewType) {
        return new ItemHolder(v);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_main,
                parent, false);
        ItemHolder holder = (ItemHolder) getHolder(view, viewType);
        return holder;
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.list_item_card_main;
    }

}
