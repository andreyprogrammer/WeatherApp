package com.andreymerkurev.weatherapp.presenter;

import android.util.Log;
import android.view.View;

import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.model.entity.CurrentCondition;
import com.andreymerkurev.weatherapp.model.entity.LangWeather;
import com.andreymerkurev.weatherapp.model.entity.Request;
import com.andreymerkurev.weatherapp.model.entity.RequestResultData;
import com.andreymerkurev.weatherapp.model.entity.Weather;
import com.andreymerkurev.weatherapp.model.entity.WeatherIconUrl;
import com.andreymerkurev.weatherapp.model.retrofit.ApiHelper;
import com.andreymerkurev.weatherapp.model.room.AppDatabase;
import com.andreymerkurev.weatherapp.model.room.WeatherCach;
import com.andreymerkurev.weatherapp.view.IWeatherView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class WeatherPresenter extends MvpPresenter<IWeatherView> {
    private static final String TAG = "app_log:WeatherPresent";

    @Inject
    ApiHelper apiHelper;

    @Inject
    AppDatabase appDatabase;

    public WeatherPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getWeatherFromInternet(String cityName) {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        Observable<RequestResultData> single = apiHelper.requestServer2(cityName);
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(weatherResp -> {
            putWeatherData(cityName, weatherResp.data);
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getViewState().setDescriptions(weatherResp.data);

        }, throwable -> { //TODO нужен пустой лист
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getWeatherFromDB(cityName);
//            CurrentCondition currentCondition = new CurrentCondition();
//            currentCondition.request.add(0,new Request());
//            getViewState().setDescriptions(currentCondition);
            Log.e(TAG, "onError " + throwable);
        });
    }

    private void putWeatherData(String cityName, CurrentCondition curCond) {
        WeatherCach weatherCach = new WeatherCach();
        weatherCach.cityName = cityName;
        weatherCach.temp = curCond.currentCondition.get(0).temp;
        weatherCach.weather = curCond.currentCondition.get(0).lang.get(0).value;
        weatherCach.weatherIconUrl = curCond.currentCondition.get(0).weatherIconUrl.get(0).value;
        weatherCach.humidity = curCond.currentCondition.get(0).humidity;
        weatherCach.pressure = curCond.currentCondition.get(0).pressure;
        weatherCach.windspeed = curCond.currentCondition.get(0).windspeed;

        Disposable disposable = appDatabase
                .iCityCashDao()
                .insertWeather(weatherCach)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    Log.d(TAG, "putWeatherData: " + id);
                }, throwable -> {
                    Log.d(TAG, "putWeatherData: " + throwable);
                });
    }

    private void getWeatherFromDB(String cityName) {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        CurrentCondition curCond = new CurrentCondition();
        curCond.request = new ArrayList<>();
        curCond.currentCondition = new ArrayList<>();
        curCond.request.add(0, new Request());
        curCond.currentCondition.add(0, new Weather());
        curCond.currentCondition.get(0).lang = new ArrayList<>();
        curCond.currentCondition.get(0).weatherIconUrl = new ArrayList<>();
        curCond.currentCondition.get(0).lang.add(new LangWeather());
        curCond.currentCondition.get(0).weatherIconUrl.add(new WeatherIconUrl());
        Disposable disposable = appDatabase
                .iCityCashDao()
                .getWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherCach -> {
                    curCond.request.get(0).query = weatherCach.cityName;
                    curCond.currentCondition.get(0).temp = weatherCach.temp;
                    curCond.currentCondition.get(0).lang.get(0).value = weatherCach.weather;
                    curCond.currentCondition.get(0).weatherIconUrl.get(0).value = weatherCach.weatherIconUrl;
                    curCond.currentCondition.get(0).humidity = weatherCach.humidity;
                    curCond.currentCondition.get(0).pressure = weatherCach.pressure;
                    curCond.currentCondition.get(0).windspeed = weatherCach.windspeed;
                    getViewState().progressBarSetVisibility(View.INVISIBLE);
                    getViewState().setDescriptions(curCond);
                }, throwable -> {
                    Log.d(TAG, "getWeatherFromDB: не удалось");
                });
    }

}
