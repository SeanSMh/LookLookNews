package com.example.sean.look.first.mvp.model.entity;

/**
 * @author Sean
 * @data 2019/1/15
 */
public class NewsInfo {
    public String imageId;  //已改为网络URL，所以改为String类型
    public String content;
    public String author;

    public NewsInfo(String imageId, String content, String author) {
        this.imageId = imageId;
        this.content = content;
        this.author = author;
    }

    public String getIamgeId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public  void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
