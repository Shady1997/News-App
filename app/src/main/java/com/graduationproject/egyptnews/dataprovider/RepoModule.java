package com.graduationproject.egyptnews.dataprovider;

import com.graduationproject.egyptnews.dataprovider.impl.HomeDataProviderImpl;
import com.graduationproject.egyptnews.webserrvices.HomeWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    @Provides
    @Singleton
    public HomeDataProvider providesHomeRepo(HomeWebService homeWebServices){
        return new HomeDataProviderImpl(homeWebServices);
    }

}