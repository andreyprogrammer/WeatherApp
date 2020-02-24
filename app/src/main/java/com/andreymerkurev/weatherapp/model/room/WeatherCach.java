package com.andreymerkurev.weatherapp.model.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_weathers")
public class WeatherCach {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "cityName")
    public String cityName;

    public String temp;
    public String weatherIconUrl;
    public String weather;
    public String windspeed;
    public String humidity;
    public String pressure;
}
