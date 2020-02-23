package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResultData {
    @Expose
    @SerializedName("data")
    public CurrentCondition data;
}
