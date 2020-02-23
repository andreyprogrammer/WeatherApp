package com.andreymerkurev.weatherapp.model.retrofit;

import com.andreymerkurev.weatherapp.model.entity.City;
import com.andreymerkurev.weatherapp.model.entity.RequestResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    public Observable<List<City>> requestServer() {
        String KEY = "37c689fe753045c58ca105748202202";
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        IApiService api = new Retrofit.Builder()
                .baseUrl("https://api.worldweatheronline.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);
        return api.getCities("Samara", "json", KEY).subscribeOn(Schedulers.io());
    }

    public Observable<RequestResult> requestServer2() {
        String KEY = "37c689fe753045c58ca105748202202";
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        IApiService api = new Retrofit.Builder()
                .baseUrl("https://api.worldweatheronline.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);
        return api.getCities2("Samara", "json", KEY).subscribeOn(Schedulers.io());
    }
}