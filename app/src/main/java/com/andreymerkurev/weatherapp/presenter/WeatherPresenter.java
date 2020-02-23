package com.andreymerkurev.weatherapp.presenter;

import android.util.Log;
import android.view.View;

import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.model.entity.RequestResultData;
import com.andreymerkurev.weatherapp.model.entity.Weather;
import com.andreymerkurev.weatherapp.model.retrofit.ApiHelper;
import com.andreymerkurev.weatherapp.view.IWeatherView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<IWeatherView> {
    private static final String TAG = "app_log - WeatherPres";
    private Weather weather;

    @Inject
    ApiHelper apiHelper;

    public WeatherPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getWeatherFromInternet(String cityName) {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        Observable<RequestResultData> single = apiHelper.requestServer2(cityName);
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(weatherResp -> {
            Log.d(TAG, "onNext: " + weatherResp);
            Log.d(TAG, "onNext: " + weatherResp.data);
            Log.d(TAG, "onNext: " + weatherResp.data.currentCondition.get(0));

            weather = weatherResp.data.currentCondition.get(0);

            for (Weather weath : weatherResp.data.currentCondition) {
//                putData(hit.webformatURL);
                Log.d(TAG, "onNext: " + weath.temp +
                        " --- :" + weath.humidity +
                        " --- :" + weath.pressure +
                        " --- :" + weath.windspeed +
                        " --- :" + weath.weatherIconUrl.get(0).value +
                        " --- :" + weath.lang.get(0).value



                );
            }
//            weather = cities.searchApi.result;
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getViewState().setDescriptions(weatherResp.data);
        }, throwable -> { //TODO нужен пустой лист
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            Log.e(TAG, "onError " + throwable);
        });
    }



}
