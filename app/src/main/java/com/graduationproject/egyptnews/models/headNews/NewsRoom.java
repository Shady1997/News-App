package com.graduationproject.egyptnews.models.headNews;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "news")
public class NewsRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    public NewsRoom(String newsAuthor, String newsTitle, String newsDescription, String newsURL, String newsImage, String publishDate, String newsContent) {
        this.newsAuthor = newsAuthor;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsURL = newsURL;
        this.newsImage = newsImage;
        this.publishDate = publishDate;
        this.newsContent = newsContent;
    }

    @ColumnInfo(name = "newsAuthor")
    private String newsAuthor;

    @ColumnInfo(name = "newsTitle")
    private String newsTitle;

    @ColumnInfo(name = "newsDescription")
    private String newsDescription;

    @ColumnInfo(name = "newsURL")
    private String newsURL;

    @ColumnInfo(name = "newsImage")
    private String newsImage;

    @ColumnInfo(name = "publishDate")
    private String publishDate;

    @ColumnInfo(name = "newsContent")
    private String newsContent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
