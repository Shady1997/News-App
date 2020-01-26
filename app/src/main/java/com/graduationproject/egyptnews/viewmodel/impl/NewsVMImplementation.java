package com.graduationproject.egyptnews.viewmodel.impl;

import androidx.lifecycle.LiveData;

import com.graduationproject.egyptnews.dataprovider.NewsDP;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;
import com.graduationproject.egyptnews.viewmodel.NewsVM;

import java.util.List;

public class NewsVMImplementation implements NewsVM {


    private NewsDP newsDP;

    public NewsVMImplementation(NewsDP newsDP) {
        this.newsDP = newsDP;
    }

    @Override
    public LiveData<List<NewsRoom>> getAllNews() {
        return newsDP.getAllNews();
    }

    @Override
    public void deleteAllNews() {  newsDP.deleteAllNews(); }

    @Override
    public void insertNews(NewsRoom news) {
        newsDP.insertNews(news);
    }

    @Override
    public void updateNews(NewsRoom news) {
        newsDP.updateNews(news);
    }

    @Override
    public void deleteNews(NewsRoom news) {
        newsDP.deleteNews(news);
    }
}
