package com.graduationproject.egyptnews.dagger;

import android.app.Application;

public class NewsApplications extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component=DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getComponent() {
        return component;
    }

}

