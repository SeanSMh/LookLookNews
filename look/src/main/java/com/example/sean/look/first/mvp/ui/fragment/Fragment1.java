package com.example.sean.look.first.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sean.look.R;
import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.example.sean.look.first.mvp.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean
 * @data 2019/1/15
 */
//第一个界面的Fragment
public class Fragment1 extends Fragment {
    private RecyclerView recyclerView;
    private List<NewsInfo> newsInfos = new ArrayList<>();  //数据源

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
        newsInfos.add(new NewsInfo(R.drawable.douzi2, "这是第一个界面"));
        newsInfos.add(new NewsInfo(R.drawable.douzi, "这是第一个界面"));
        return recyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanctState) {
        super.onActivityCreated(savedInstanctState);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(recyclerView.getContext()));
        //在这传送不同的数据？？用EventBus接收数据
        recyclerView.setAdapter(new RecyclerViewAdapter(newsInfos));
    }

    //退出该界面时删除所有数据
    @Override
    public void onDestroy() {
        super.onDestroy();
        newsInfos.clear();
    }

}
