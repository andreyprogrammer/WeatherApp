package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResult {
    @Expose
    @SerializedName("search_api")
    public Result searchApi;
}
