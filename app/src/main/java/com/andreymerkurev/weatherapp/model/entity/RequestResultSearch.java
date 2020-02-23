package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResultSearch {
    @Expose
    @SerializedName("search_api")
    public Result searchApi;
}
