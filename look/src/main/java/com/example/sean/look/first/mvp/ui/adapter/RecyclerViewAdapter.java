package com.example.sean.look.first.mvp.ui.adapter;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.example.sean.look.R;
import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.example.sean.look.first.mvp.ui.ItemHolder.ItemHolder;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.ArmsUtils;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                parent, false);
        ItemHolder holder = (ItemHolder) getHolder(view, viewType);

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArmsUtils.makeText(view.getContext(), "这是一张回家的车票");
                //后期要改为点击进入详情界面

            }
        });
        return holder;
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.list_item;
    }

}
