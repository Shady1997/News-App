package com.graduationproject.egyptnews.viewmodel;

import androidx.lifecycle.LiveData;

import com.graduationproject.egyptnews.models.headNews.NewsRoom;

import java.util.List;


public interface NewsVM {


    LiveData<List<NewsRoom>> getAllNews();

    void deleteAllNews();

    void insertNews(NewsRoom insertNews);

    void updateNews(NewsRoom updateNews);

    void deleteNews(NewsRoom deleteNews);
}
