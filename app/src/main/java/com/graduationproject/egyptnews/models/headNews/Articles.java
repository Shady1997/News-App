package com.graduationproject.egyptnews.models.headNews;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Articles implements Serializable {

    @SerializedName("source")
    private Sources newsSources;

    @SerializedName("author")
    private String newsAuthor;

    @SerializedName("title")
    private String newsTitle;

    @SerializedName("description")
    private String newsDescription;

    @SerializedName("url")
    private String newsURL;

    @SerializedName("urlToImage")
    private String newsImage;

    @SerializedName("publishedAt")
    private String publishDate;

    @SerializedName("content")
    private String newsContent;


    public Sources getNewsSources() {
        return newsSources;
    }

    public void setNewsSources(Sources newsSources) {
        this.newsSources = newsSources;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }



}
