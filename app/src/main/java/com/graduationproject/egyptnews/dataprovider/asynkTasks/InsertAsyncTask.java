package com.graduationproject.egyptnews.dataprovider.asynkTasks;

import android.os.AsyncTask;

import com.graduationproject.egyptnews.dao.NewsDAO;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;


public class InsertAsyncTask extends AsyncTask<NewsRoom, Void, Void> {

    private NewsDAO asyncTaskDAO;

    public InsertAsyncTask(NewsDAO newsDAO) {
        this.asyncTaskDAO = newsDAO;
    }


    @Override
    protected Void doInBackground(NewsRoom... dbNewssEntities) {
        asyncTaskDAO.insertNews(dbNewssEntities[0]);
        return null;
    }
}
