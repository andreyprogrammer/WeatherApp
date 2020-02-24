package com.andreymerkurev.weatherapp.app;

import android.app.Application;

import com.andreymerkurev.weatherapp.model.PicassoLoader;
import com.andreymerkurev.weatherapp.model.retrofit.ApiHelper;
import com.andreymerkurev.weatherapp.model.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    ApiHelper provideApiHelper() {
        return new ApiHelper();
    }

    @Singleton
    @Provides
    PicassoLoader providePicassoLoader() {
        return new PicassoLoader();
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase() {
        return App.getAppDatabase();
    }
}
