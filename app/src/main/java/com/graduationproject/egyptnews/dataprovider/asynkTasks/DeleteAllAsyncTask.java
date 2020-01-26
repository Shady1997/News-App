package com.graduationproject.egyptnews.dataprovider.asynkTasks;

import android.os.AsyncTask;

import com.graduationproject.egyptnews.dao.NewsDAO;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;

public class DeleteAllAsyncTask extends AsyncTask<NewsRoom, Void, Void> {

    private NewsDAO asyncTaskDAO;

    public DeleteAllAsyncTask(NewsDAO noteDAO) {
        this.asyncTaskDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NewsRoom... dbNewsEntities) {
        asyncTaskDAO.deleteAllNews();
        return null;
    }
}
