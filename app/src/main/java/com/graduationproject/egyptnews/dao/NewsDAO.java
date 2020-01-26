package com.graduationproject.egyptnews.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.graduationproject.egyptnews.models.headNews.NewsRoom;

import java.util.List;

@Dao
public interface NewsDAO {

    @Query("SELECT * FROM news")
    LiveData<List<NewsRoom>> getAllNews();

    @Query("DELETE FROM news")
    void deleteAllNews();

    @Insert
    void insertNews(NewsRoom insertNewsEntity);

    @Update
    void updateNews(NewsRoom updateNewsEntity);

    @Delete
    void deleteNews(NewsRoom deleteNewsEntity);
}
