package com.graduationproject.egyptnews.dataprovider.asynkTasks;

import android.os.AsyncTask;

import com.graduationproject.egyptnews.dao.NewsDAO;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;


public class DeleteAsyncTask extends AsyncTask<NewsRoom, Void, Void> {

    private NewsDAO asyncTaskDAO;

    public DeleteAsyncTask(NewsDAO noteDAO) {
        this.asyncTaskDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NewsRoom... dbNewsEntities) {
        asyncTaskDAO.deleteNews(dbNewsEntities[0]);
        return null;
    }
}
