package com.example.sean.look.first.mvp.ui.ItemHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sean.look.R;
import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.jess.arms.base.BaseHolder;

/**
 * @author Sean
 * @data 2019/1/15
 */
public class ItemHolder extends BaseHolder<NewsInfo> {

    public ImageView imageId;
    public TextView content;

    public ItemHolder(View itemView) {
        super(itemView);

        imageId = (ImageView) itemView.findViewById(R.id.image);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    @Override
    public void setData(NewsInfo data, int position) {

        content.setText(data.getContent());

        //Glide加载图片
        RequestOptions options = new RequestOptions()
                .override(100, 100);  //指定图片大小
        Glide.with(itemView).load(data.getIamgeId()).apply(options).into(imageId);

    }
}
