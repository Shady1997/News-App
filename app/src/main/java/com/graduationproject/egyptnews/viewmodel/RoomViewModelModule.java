package com.graduationproject.egyptnews.viewmodel;

import com.graduationproject.egyptnews.dataprovider.NewsDP;
import com.graduationproject.egyptnews.viewmodel.impl.NewsVMImplementation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomViewModelModule {

    @Provides
    @Singleton
    public NewsVM providesNewsVM(NewsDP newsDP) {
        return new NewsVMImplementation(newsDP);
    }
}
