package com.andreymerkurev.weatherapp.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherCach.class, CityCache.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IWeatherCachDao iCityCashDao();
}
