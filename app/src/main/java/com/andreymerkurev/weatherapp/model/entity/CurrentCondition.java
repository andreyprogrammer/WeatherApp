package com.andreymerkurev.weatherapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CurrentCondition {
    @Expose
    @SerializedName("request")
    public List<Request> request; //TODO list

    @Expose
    @SerializedName("current_condition")
    public List<Weather> currentCondition;
}
