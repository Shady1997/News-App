package com.graduationproject.egyptnews.viewmodel;

import com.graduationproject.egyptnews.dataprovider.HomeDataProvider;
import com.graduationproject.egyptnews.viewmodel.impl.HomeViewModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    @Singleton
    public HomeViewModel providesHomeViewModel(HomeDataProvider homeRepository){
        return new HomeViewModelImpl(homeRepository);
    }
}
