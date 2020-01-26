package com.graduationproject.egyptnews.webserrvices;

import com.graduationproject.egyptnews.utils.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WebServiceModule {

    @Provides
    @Singleton
    public HomeWebService providesHomeWebService() {
        return RetrofitFactory.createService(HomeWebService.class);
    }
}
