package com.graduationproject.egyptnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.graduationproject.egyptnews.dao.NewsDAO;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;

@Database(entities = {NewsRoom.class}, version = 1 ,exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDAO getNewsDao();
}
