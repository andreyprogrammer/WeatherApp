package com.andreymerkurev.weatherapp.presenter;

import android.util.Log;
import android.view.View;

import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.model.entity.City;
import com.andreymerkurev.weatherapp.model.entity.RequestResultSearch;
import com.andreymerkurev.weatherapp.model.retrofit.ApiHelper;
import com.andreymerkurev.weatherapp.view.ISelectCityView;
import com.andreymerkurev.weatherapp.view.IViewHolder;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SelectCityPresenter extends MvpPresenter<ISelectCityView> {
    private static final String TAG = "app_log - SelectCityPre";
    private RecyclerSelectCityPresenter recyclerSelectCityPresenter;
    private List<City> resultCities;

    @Inject
    ApiHelper apiHelper;

    public SelectCityPresenter() {
        App.getAppComponent().inject(this);
        recyclerSelectCityPresenter = new RecyclerSelectCityPresenter();
    }

    public RecyclerSelectCityPresenter getRecyclerSelectCityPresenter() {
        return recyclerSelectCityPresenter;
    }

    public void getAllCitiesFromInternet(String cityName) {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        Observable<RequestResultSearch> single = apiHelper.requestServer(cityName);
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(cities -> {

            for (City city : cities.searchApi.result) {
//                putData(hit.webformatURL);
                Log.d(TAG, "onNext: city.areaName: " + city.areaName.get(0).value +
                        " --- city.country:" + city.country.get(0).value +
                        " lat: " + city.latitude + " lon: " + city.longitude);
            }
            resultCities = cities.searchApi.result;
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getViewState().updateRecyclerView();
        }, throwable -> { //TODO нужен пустой лист
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            Log.e(TAG, "onError " + throwable);
        });
    }

//    public void getAllCitiesFromInternet2() {
//        getViewState().progressBarSetVisibility(View.VISIBLE);
//        Observable<RequestResultSearch> single = apiHelper.requestServer2();
//        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(cities -> {
////            Log.d(TAG, "onNext1: " + cities);
////            Log.d(TAG, "onNext2: " + cities.searchApi);
////            Log.d(TAG, "onNext3 size: " + cities.searchApi.result.size());
////            Log.d(TAG, "onNext4: " + cities.searchApi.result.get(0).country);
//
//            for (City city : cities.searchApi.result) {
////                putData(hit.webformatURL);
//                Log.d(TAG, "onNext: city.areaName: " + city.areaName.get(0).value +
//                        " --- city.country:" + city.country.get(0).value +
//                        " lat: " + city.latitude + " lon: " + city.longitude);
//            }
//            resultCities = cities.searchApi.result;
//            getViewState().progressBarSetVisibility(View.INVISIBLE);
//            getViewState().updateRecyclerView();
//        }, throwable -> {
//            Log.e(TAG, "onError " + throwable);
//        });
//    }


    private class RecyclerSelectCityPresenter implements IRecyclerSelectCityPresenter {
        @Override
        public void bindView(IViewHolder holder) {
            holder.setWeather(resultCities.get(holder.getPos()).areaName.get(0).value,
                    resultCities.get(holder.getPos()).country.get(0).value,
                    resultCities.get(holder.getPos()).latitude,
                    resultCities.get(holder.getPos()).longitude);
        }

        @Override
        public int getItemCount() {
            if (resultCities != null) {
                return resultCities.size();
            }
            return 0;
        }

        @Override
        public void onClick(View v, int position) {
            getViewState().onClick(v, resultCities.get(position).areaName.get(0).value);
        }
    }

}
