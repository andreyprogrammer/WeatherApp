package com.andreymerkurev.weatherapp.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_cities")
public class CityCach {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String webformatURL;

    @Override
    public String toString() {
        return "CityCach{" +
                "id=" + id +
                ", webformatURL='" + webformatURL + '\'' +
                '}';
    }
}
