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
    @SerializedName("latitude")
    public String latitude;

    @Expose
    @SerializedName("longitude")
    public String longitude;
}