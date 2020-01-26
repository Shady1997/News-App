package com.graduationproject.egyptnews.dataprovider.asynkTasks;

import android.os.AsyncTask;

import com.graduationproject.egyptnews.dao.NewsDAO;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;


public class UpdateAsyncTask extends AsyncTask<NewsRoom, Void, Void> {

    private NewsDAO asyncTaskDAO;

    public UpdateAsyncTask(NewsDAO newsDAO) {
        this.asyncTaskDAO = newsDAO;
    }

    @Override
    protected Void doInBackground(NewsRoom... dbNewsEntities) {
        asyncTaskDAO.updateNews(dbNewsEntities[0]);
        return null;
    }
}

