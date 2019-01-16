package com.example.sean.look.first.mvp.presenter;

import android.app.Application;

import com.example.sean.look.first.mvp.event.NewsEvent;
import com.example.sean.look.first.mvp.event.NewsEvent2;
import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.example.sean.look.first.mvp.model.entity.NewsInfos;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;

import javax.inject.Inject;

import com.example.sean.look.first.mvp.contract.firstContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 01/14/2019 19:46
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class firstPresenter extends BasePresenter<firstContract.Model, firstContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public firstPresenter(firstContract.Model model, firstContract.View rootView) {
        super(model, rootView);
    }

    public void requestFromModel(String top, String key) {
        mModel.getNews(top, key);
    }

    //接收model返回的数据，并在这里分发给各个Fragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void accpet(List<NewsInfos.ResultBean.DataBean> beanList) {
        List<NewsInfos.ResultBean.DataBean> beans = beanList;
        Timber.d("----->%s", beans.get(0).getTitle());
        Timber.d("----->%s", beans.size());


        //暂时划分为两个list,分发给前两个Fragment
        List<NewsInfo> list1 = new ArrayList<>();
        List<NewsInfo> list2 = new ArrayList<>();

        for (int i = 0; i < beans.size() / 2; i++) {
            NewsInfo a = new NewsInfo(beans.get(i).getThumbnail_pic_s(), beans.get(i).getTitle(),
                    beans.get(i).getAuthor_name());
            list1.add(a);
        }

        NewsEvent event1 = new NewsEvent(list1);
        Timber.d("----->%s", list1.size());
        EventBus.getDefault().postSticky(event1);    //发送数据给第一个Fragment

        for (int i = beans.size() / 2; i < beans.size(); i++) {
            NewsInfo a = new NewsInfo(beans.get(i).getThumbnail_pic_s(), beans.get(i).getTitle(),
                    beans.get(i).getAuthor_name());
            list2.add(a);
        }
        Timber.d("----->%s", list2.size());
        NewsEvent2 event2 = new NewsEvent2(list2);
        EventBus.getDefault().postSticky(event2);     //发送数据给第二个Fragment

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
