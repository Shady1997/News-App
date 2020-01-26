package com.graduationproject.egyptnews.dataprovider;

import androidx.lifecycle.LiveData;

import com.graduationproject.egyptnews.models.headNews.NewsRoom;

import java.util.List;

public interface NewsDP {

    LiveData<List<NewsRoom>> getAllNews();

    void deleteAllNews();

    void insertNews(NewsRoom insertNews);

    void updateNews(NewsRoom updateNews);

    void deleteNews(NewsRoom deleteNews);
}
