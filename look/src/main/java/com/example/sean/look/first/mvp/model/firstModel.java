package com.example.sean.look.first.mvp.model;

import android.app.Application;

import com.example.sean.look.first.api.service.GetNews;
import com.example.sean.look.first.mvp.model.entity.NewsInfos;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.sean.look.first.mvp.contract.firstContract;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

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
public class firstModel extends BaseModel implements firstContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public firstModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    //获取新闻消息
    @Override
    public void getNews(String top, String key) {
        Observable.just(mRepositoryManager.obtainRetrofitService(
                GetNews.class).getNews(top, key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<NewsInfos>>() {
                    @Override
                    public void accept(Observable<NewsInfos> newsInfoObservable) throws Exception {
                        newsInfoObservable.subscribe(new Consumer<NewsInfos>() {
                            @Override
                            public void accept(NewsInfos newsInfos) throws Exception {
                                Timber.d("----->%s", newsInfos.getResult().getData().get(0));
                                List<NewsInfos.ResultBean.DataBean> dataBeanList =
                                        newsInfos.getResult().getData();
                                //发送得到的新闻信息
                                EventBus.getDefault().post(dataBeanList);

                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }

}