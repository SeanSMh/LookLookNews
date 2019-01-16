package com.example.sean.look.first.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.example.sean.look.R;
import com.example.sean.look.first.mvp.ui.adapter.FragmentAdapter;

import com.example.sean.look.first.mvp.ui.fragment.FragmentA;
import com.example.sean.look.first.mvp.ui.fragment.FragmentB;
import com.example.sean.look.first.mvp.ui.fragment.FragmentC;
import com.example.sean.look.first.mvp.ui.fragment.FragmentD;
import com.example.sean.look.first.mvp.ui.fragment.FragmentE;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.sean.look.first.di.component.DaggerfirstComponent;
import com.example.sean.look.first.mvp.contract.firstContract;
import com.example.sean.look.first.mvp.presenter.firstPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


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
public class firstActivity extends BaseActivity<firstPresenter> implements firstContract.View {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerfirstComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_first; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        /*String top = "top";
        String key = "aac8eee051f88de226cbf3ecf544720f";
        mPresenter.requestFromModel(top, key);*/
        setSupportActionBar(toolbar);
        initViewpager();
    }

    private void initViewpager() {
        List<String> titles = new ArrayList<>();
        titles.add("体育");
        titles.add("科技");
        titles.add("明星");
        titles.add("购物");
        titles.add("社会");

        //标签栏添加标题
        for(int i=0; i<titles.size(); i++){
            tabs.addTab(tabs.newTab().setText(titles.get(i)));
        }

        //创建fragments,将界面添加进去
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
        fragments.add(new FragmentD());
        fragments.add(new FragmentE());

        //添加Viewpager适配器
        FragmentAdapter fragmentAdapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(fragmentAdapter);
        //将TabLayout和viewPager关联起来
        tabs.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabs.setTabsFromPagerAdapter(fragmentAdapter);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
