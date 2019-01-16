package com.example.sean.look.first.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.example.sean.look.first.mvp.ui.fragment.FragmentA;
import com.example.sean.look.first.mvp.ui.fragment.FragmentB;
import com.example.sean.look.first.mvp.ui.fragment.FragmentC;
import com.example.sean.look.first.mvp.ui.fragment.FragmentD;
import com.example.sean.look.first.mvp.ui.fragment.FragmentE;
import com.jess.arms.di.component.AppComponent;

import com.example.sean.look.first.di.module.firstModule;
import com.example.sean.look.first.mvp.contract.firstContract;

import com.jess.arms.di.scope.ActivityScope;
import com.example.sean.look.first.mvp.ui.activity.firstActivity;
import com.example.sean.look.first.mvp.ui.fragment.firstFragment;


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
@Component(modules = firstModule.class, dependencies = AppComponent.class)
public interface firstComponent {
    void inject(firstActivity activity);

    void inject(firstFragment fragment);
    void inject(FragmentA fragmentA);
    void inject(FragmentB fragmentB);
    void inject(FragmentC fragmentC);
    void inject(FragmentD fragmentD);
    void inject(FragmentE fragmentE);

    @Component.Builder
    interface Builder {
        @BindsInstance
        firstComponent.Builder view(firstContract.View view);

        firstComponent.Builder appComponent(AppComponent appComponent);

        firstComponent build();
    }
}