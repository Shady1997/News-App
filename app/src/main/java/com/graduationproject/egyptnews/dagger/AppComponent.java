package com.graduationproject.egyptnews.dagger;

import com.graduationproject.egyptnews.views.fragments.mainNewsFragments.HeadNewsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(HeadNewsFragment headNews);
}

