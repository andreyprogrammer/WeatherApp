package com.andreymerkurev.weatherapp.model.retrofit;

import com.andreymerkurev.weatherapp.model.entity.RequestResultData;
import com.andreymerkurev.weatherapp.model.entity.RequestResultSearch;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiService {

    @GET("premium/v1/search.ashx")
    Observable<RequestResultSearch> getCities(@Query("query") String query,
                                              @Query("format") String format,
                                              @Query("key") String key);

    @GET("premium/v1/weather.ashx")
    Observable<RequestResultData> getWeather(@Query("query") String query,
                                             @Query("format") String format,
                                             @Query("key") String key,
                                             @Query("lang") String lang);

}
