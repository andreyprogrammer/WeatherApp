package com.andreymerkurev.weatherapp.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ICityCachDao {
    @Query("SELECT * FROM table_cities")
    Single<List<CityCach>> getAll();

    @Insert
    Single<Long> insert(CityCach cityCach);
}
