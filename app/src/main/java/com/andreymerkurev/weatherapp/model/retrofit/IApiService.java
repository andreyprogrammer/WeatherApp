package com.andreymerkurev.weatherapp.model.retrofit;

import com.andreymerkurev.weatherapp.model.entity.City;
import com.andreymerkurev.weatherapp.model.entity.RequestResult;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiService {
    @GET("premium/v1/search.ashx")
    Observable<List<City>> getCities(@Query("query") String query,
                                     @Query("format") String format,
                                     @Query("key") String key);

    @GET("premium/v1/search.ashx")
    Observable<RequestResult> getCities2(@Query("query") String query,
                                         @Query("format") String format,
                                         @Query("key") String key);


}
