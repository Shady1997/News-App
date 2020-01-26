package com.graduationproject.egyptnews.viewmodel;

import com.graduationproject.egyptnews.models.headNews.News;

import io.reactivex.Single;
import retrofit2.http.Path;

public interface HomeViewModel {

    Single<News> getNews(@Path("id") String competitionId);

}
