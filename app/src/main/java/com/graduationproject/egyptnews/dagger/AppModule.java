package com.graduationproject.egyptnews.dagger;

import android.app.Application;
import android.content.Context;


import androidx.room.Room;

import com.graduationproject.egyptnews.database.NewsDatabase;
import com.graduationproject.egyptnews.dataprovider.RepoModule;
import com.graduationproject.egyptnews.dataprovider.RoomDataProviderModule;
import com.graduationproject.egyptnews.viewmodel.RoomViewModelModule;
import com.graduationproject.egyptnews.viewmodel.ViewModelModule;
import com.graduationproject.egyptnews.webserrvices.WebServiceModule;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class, RepoModule.class, WebServiceModule.class, RoomViewModelModule.class, RoomDataProviderModule.class})
class AppModule {

    private Application app;

    AppModule(Application app) {
        this.app = app;
    }

    @Provides
    public Context providesContext() {
        return app;
    }

    @Provides
    @Singleton
    public NewsDatabase providesDatabaseObject(Context context) {
        return Room.databaseBuilder(context, NewsDatabase.class, "news")
                .fallbackToDestructiveMigration()
                .build();
    }

}
