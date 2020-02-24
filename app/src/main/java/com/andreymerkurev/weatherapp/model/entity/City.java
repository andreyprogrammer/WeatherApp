package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {
    @Expose
    @SerializedName("areaName")
    public List<AreaName> areaName;

    @Expose
    @SerializedName("country")
    public List<Country> country;

    @Expose
    @SerializedName("latitude") //TODO delete
    public String latitude;

    @Expose
    @SerializedName("longitude")
    public String longitude;

    public City(List<AreaName> areaName, List<Country> country) {
        this.areaName = areaName;
        this.country = country;
    }
}