package com.andreymerkurev.weatherapp.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface IWeatherCachDao {
    @Query("SELECT * FROM table_weathers WHERE cityName LIKE :city LIMIT 1")
    Single<WeatherCach> getWeather(String city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertWeather(WeatherCach weatherCach);

    @Query("SELECT * FROM table_cities WHERE cityName LIKE :city")
    Single<List<CityCache>> getCities(String city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long[]> insertCities(List<CityCache> cityCache);
}
