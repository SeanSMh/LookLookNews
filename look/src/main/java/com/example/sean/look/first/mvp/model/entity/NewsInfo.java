package com.example.sean.look.first.mvp.model.entity;

/**
 * @author Sean
 * @data 2019/1/15
 */
public class NewsInfo {
    public int imageId;
    public String content;

    public NewsInfo(int imageId, String content) {
        this.imageId = imageId;
        this.content = content;
    }

    public int getIamgeId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public  void setContent(String content) {
        this.content = content;
    }
}
