package com.graduationproject.egyptnews.dataprovider.impl;

import com.graduationproject.egyptnews.dataprovider.HomeDataProvider;
import com.graduationproject.egyptnews.models.headNews.News;
import com.graduationproject.egyptnews.webserrvices.HomeWebService;

import io.reactivex.Single;

public class HomeDataProviderImpl implements HomeDataProvider {

    HomeWebService homeWebServices;

    public HomeDataProviderImpl(HomeWebService homeWebServices) {
        this.homeWebServices = homeWebServices;
    }

    @Override
    public Single<News> getNews(String competitionId) {
        return homeWebServices.getNews( competitionId);
    }
}
