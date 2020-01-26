package com.graduationproject.egyptnews.dataprovider;

import com.graduationproject.egyptnews.database.NewsDatabase;
import com.graduationproject.egyptnews.dataprovider.impl.NewsDPImplementation;
import com.graduationproject.egyptnews.viewmodel.NewsVM;
import com.graduationproject.egyptnews.viewmodel.impl.NewsVMImplementation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomDataProviderModule {

    @Provides
    @Singleton
    public NewsDP providesNewsDP(NewsDatabase persistence) {
        return new NewsDPImplementation(persistence);
    }

}
