package com.example.sean.look.first.mvp.event;

import com.example.sean.look.first.mvp.model.entity.NewsInfo;

import java.util.List;

/**
 * @author Sean
 * @data 2019/1/16
 */
public class NewsEvent {
    private List<NewsInfo> list;

    public NewsEvent(List<NewsInfo> list) {
        this.list = list;
    }

    public List<NewsInfo> getList() {
        return list;
    }

    public void setList(List<NewsInfo> list) {
        this.list = list;
    }
}
