package com.graduationproject.egyptnews.viewmodel.impl;

import com.graduationproject.egyptnews.dataprovider.HomeDataProvider;
import com.graduationproject.egyptnews.models.headNews.News;
import com.graduationproject.egyptnews.viewmodel.HomeViewModel;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModelImpl implements HomeViewModel {

    HomeDataProvider homeRepo;

    public HomeViewModelImpl(HomeDataProvider homeRepo) {
        this.homeRepo = homeRepo;
    }

    @Override
    public Single<News> getNews(String competitionId) {
        return homeRepo.getNews(competitionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
