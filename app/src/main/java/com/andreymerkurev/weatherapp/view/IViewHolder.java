package com.andreymerkurev.weatherapp.view;

public interface IViewHolder {
    int getPos();
    void setWeather(String name, String country, String lat, String lon);
}
