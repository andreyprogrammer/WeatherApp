package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LangWeather {
    @Expose
    @SerializedName("value")
    public String value;
}
