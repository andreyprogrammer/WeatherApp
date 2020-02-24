package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaName {
    @Expose
    @SerializedName("value")
    public String value;

    public AreaName(String value) {
        this.value = value;
    }
}
