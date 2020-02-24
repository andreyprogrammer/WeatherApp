package com.andreymerkurev.weatherapp.model.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_cities")
public class CityCache {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "cityName")
    public String cityName;

    public String country;

    public CityCache(String cityName, String country) {
        this.cityName = cityName;
        this.country = country;
    }
}
