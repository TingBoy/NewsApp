package com.example.tingboy.newsapp.model;

/**
 * Created by tingboy on 6/29/17.
 */

public class NewsItem {

    private String author;
    private String title;
    private String desc;
    private String date;
    private String url;
    private String imgUrl;

    public NewsItem(String author, String title, String desc, String date, String url, String imgUrl) {
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.url = url;
        this.imgUrl = imgUrl;
    }

    public String getAuthor() {return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url; }

    public String getimgUrl() { return imgUrl;}

    public void setimgUrl(String imgUrl) { this.imgUrl = imgUrl; }
}
