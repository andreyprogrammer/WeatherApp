package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @Expose
    @SerializedName("value")
    public String value;

    public Country(String value) {
        this.value = value;
    }
}
