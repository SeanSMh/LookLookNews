package com.example.sean.look.first.mvp.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toolbar;

/**
 * @author Sean
 * @data 2019/1/15
 */
//贝塞尔曲线波浪效果
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BezierView extends View implements View.OnClickListener {

    private Paint mPaint;  //画笔
    private Path mPath;
    private int mWaveLength;  //波浪宽度
    private int mScreenWidth;  //屏幕宽度
    private int mScreenHeight;  //屏幕高度
    private int mCentery;
    private int mWaveCount;   //波浪个数
    private int offset;

    private ValueAnimator animator;   //动画效果

    public BezierView(Context context) {
        super(context);
        initPaint();
    }

    public BezierView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initPaint();  //初始化画笔
    }

    public BezierView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    //绘制过程
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLength, mCentery);
        for(int i=0; i<mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offset,
                    mCentery + 60, -mWaveLength / 2 + i * mWaveLength + offset,
                    mCentery);
            mPath.quadTo(-mWaveLength /4 + i * mWaveLength + offset,
                    mCentery - 60, i * mWaveLength + offset, mCentery);
        }

        //下面这三句代码形成了一个封闭区间，让曲线以下的面积填充一种颜色，
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();

        //这里就把封闭图形填充了颜色，画笔什么颜色，就填充什么颜色
        canvas.drawPath(mPath, mPaint);
    }

    //初始化画笔
    public void initPaint()  {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.YELLOW);

        mWaveLength = 800;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //计算屏幕能容纳多少个波形
        mPath = new Path();
        mScreenHeight = h;
        mScreenWidth = w;
        mCentery = h / 2;
        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);  //计算波形的个数
        setOnClickListener(this);
    }

    //动画设置
    @Override
    public void onClick(View v) {
        animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(1000);   //动画时间
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);  //重复次数，无限
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();  //重绘
            }
        });
        animator.start();  //开始动画
    }
}
