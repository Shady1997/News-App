package com.graduationproject.egyptnews.dataprovider;

import com.graduationproject.egyptnews.models.headNews.News;

import io.reactivex.Single;
import retrofit2.http.Path;

public interface HomeDataProvider {

    Single<News> getNews(@Path("id") String competitionId);

}
