package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    @Expose
    @SerializedName("temp_C")
    public String temp;

    @Expose
    @SerializedName("weatherIconUrl")
    public List<WeatherIconUrl> weatherIconUrl;

    @Expose
    @SerializedName("lang_ru")
    public List<LangWeather> lang;

    @Expose
    @SerializedName("windspeedKmph")
    public String windspeed;

    @Expose
    @SerializedName("humidity")
    public String humidity;

    @Expose
    @SerializedName("pressure")
    public String pressure;
}
