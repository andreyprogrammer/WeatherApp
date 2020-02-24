package com.andreymerkurev.weatherapp.app;

import android.app.Application;

import androidx.room.Room;

import com.andreymerkurev.weatherapp.model.room.AppDatabase;

public class App extends Application {
    private static AppDatabase appDatabase;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = generateAppComponent();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_database").build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent generateAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
