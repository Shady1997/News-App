package com.graduationproject.egyptnews.dataprovider.impl;

import androidx.lifecycle.LiveData;

import com.graduationproject.egyptnews.database.NewsDatabase;
import com.graduationproject.egyptnews.dataprovider.NewsDP;
import com.graduationproject.egyptnews.dataprovider.asynkTasks.DeleteAllAsyncTask;
import com.graduationproject.egyptnews.dataprovider.asynkTasks.DeleteAsyncTask;
import com.graduationproject.egyptnews.dataprovider.asynkTasks.InsertAsyncTask;
import com.graduationproject.egyptnews.dataprovider.asynkTasks.UpdateAsyncTask;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;

import java.util.List;

public class NewsDPImplementation implements NewsDP {

    private NewsDatabase newsDatabase;

    public NewsDPImplementation(NewsDatabase persistence) {
        this.newsDatabase = persistence;
    }


    @Override
    public LiveData<List<NewsRoom>> getAllNews() {
        return newsDatabase.getNewsDao().getAllNews();
    }

    @Override
    public void deleteAllNews() {new DeleteAllAsyncTask(newsDatabase.getNewsDao()).execute();}

    @Override
    public void insertNews(NewsRoom news) {
        new InsertAsyncTask(newsDatabase.getNewsDao()).execute(news);
    }

    @Override
    public void updateNews(NewsRoom news) {
        new UpdateAsyncTask(newsDatabase.getNewsDao()).execute(news);
    }

    @Override
    public void deleteNews(NewsRoom news) {
        new DeleteAsyncTask(newsDatabase.getNewsDao()).execute(news);
    }
}
