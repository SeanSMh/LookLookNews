package com.example.sean.look.first.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sean.look.R;
import com.example.sean.look.first.di.component.DaggerfirstComponent;
import com.example.sean.look.first.mvp.contract.firstContract;
import com.example.sean.look.first.mvp.event.NewsEvent;
import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.example.sean.look.first.mvp.presenter.firstPresenter;
import com.example.sean.look.first.mvp.ui.adapter.RecyclerViewAdapter;
import com.example.sean.look.first.mvp.ui.widget.WrapContentLinearLayoutManager;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import timber.log.Timber;

/**
 * @author Sean
 * @data 2019/1/16
 */

public class FragmentE extends BaseFragment<firstPresenter> implements firstContract.View {

    private RecyclerView recyclerView;
    private List<NewsInfo> newsInfos;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerfirstComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
        return recyclerView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void acceptNews(NewsEvent event) {
        newsInfos = event.getList();
        Timber.d("acceptNews----->%s", newsInfos.get(0).getContent());
        Timber.d("acceptNews----->%s", newsInfos.size());
        recyclerView.setLayoutManager(new
                WrapContentLinearLayoutManager(recyclerView.getContext()));
        //在这传送不同的数据？？用EventBus接收数据
        Timber.d("onActivityCreated----->%s", newsInfos.size());
        recyclerView.setAdapter(new RecyclerViewAdapter(newsInfos));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        String top = "top";
        String key = "aac8eee051f88de226cbf3ecf544720f";
        mPresenter.requestFromModel(top, key);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}

