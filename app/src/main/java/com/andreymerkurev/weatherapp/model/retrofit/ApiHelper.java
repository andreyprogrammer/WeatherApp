package com.andreymerkurev.weatherapp.model.retrofit;

import com.andreymerkurev.weatherapp.model.entity.RequestResultData;
import com.andreymerkurev.weatherapp.model.entity.RequestResultSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private String KEY = "37c689fe753045c58ca105748202202";
    public Observable<RequestResultSearch> requestServer(String cityName) {
////        String KEY = "37c689fe753045c58ca105748202202";
//        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .create();
//
//        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
//
//        IApiService api = new Retrofit.Builder()
//                .baseUrl("https://api.worldweatheronline.com")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(gsonConverterFactory)
//                .build()
//                .create(IApiService.class);
        return createApi().getCities(cityName, "json", KEY).subscribeOn(Schedulers.io());
    }

    public Observable<RequestResultData> requestServer2(String cityName) {

//        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .create();
//
//        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
//
//        IApiService api = new Retrofit.Builder()
//                .baseUrl("https://api.worldweatheronline.com")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(gsonConverterFactory)
//                .build()
//                .create(IApiService.class);

        return createApi()
                .getWeather(cityName, "json", KEY, "ru")
                .subscribeOn(Schedulers.io());
    }

    public IApiService createApi() {
//        String KEY = "37c689fe753045c58ca105748202202";
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        return new Retrofit.Builder()
                .baseUrl("https://api.worldweatheronline.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);
    }

}