package com.graduationproject.egyptnews.webserrvices;

import com.graduationproject.egyptnews.models.headNews.News;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeWebService {

    @GET("{id}?country=eg")
    Single<News> getNews(@Path("id") String competitionId);

}
