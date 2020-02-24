package com.andreymerkurev.weatherapp.presenter;

import android.util.Log;
import android.view.View;

import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.model.entity.AreaName;
import com.andreymerkurev.weatherapp.model.entity.City;
import com.andreymerkurev.weatherapp.model.entity.Country;
import com.andreymerkurev.weatherapp.model.entity.CurrentCondition;
import com.andreymerkurev.weatherapp.model.entity.RequestResultSearch;
import com.andreymerkurev.weatherapp.model.retrofit.ApiHelper;
import com.andreymerkurev.weatherapp.model.room.AppDatabase;
import com.andreymerkurev.weatherapp.model.room.CityCache;
import com.andreymerkurev.weatherapp.view.ISelectCityView;
import com.andreymerkurev.weatherapp.view.IViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SelectCityPresenter extends MvpPresenter<ISelectCityView> {
    private static final String TAG = "app_log - SelectCityPre";
    private RecyclerSelectCityPresenter recyclerSelectCityPresenter;
    private List<City> resultCities;

    @Inject
    ApiHelper apiHelper;

    @Inject
    AppDatabase appDatabase;

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
            resultCities = cities.searchApi.result;
            putCityData(resultCities);
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getViewState().updateRecyclerView();
        }, throwable -> { //TODO нужен пустой лист
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getCitiesFromDB(cityName);
            Log.e(TAG, "onError ----- no connection ------" + throwable);

        });
    }

    private void putCityData(List<City> listResultCities) {
        List<CityCache> listCityCache = new ArrayList<>();
        int i = 0;
        for (City city : listResultCities) {
            listCityCache.add(i, new CityCache(city.areaName.get(0).value,
                    city.country.get(0).value));
            i++;
        }

        Disposable disposable = appDatabase
                .iCityCashDao()
                .insertCities(listCityCache)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    Log.d(TAG, "putData: " + id);
                }, throwable -> {
                    Log.d(TAG, "putData: " + throwable);
                });
    }

    private void getCitiesFromDB(String cityName) {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        CurrentCondition curCond = new CurrentCondition();
        Disposable disposable = appDatabase
                .iCityCashDao()
                .getCities(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCitiesCash -> {
                    int i = 0;
                    if (resultCities != null) resultCities.clear();
                    List<AreaName> areaNames = new ArrayList<>();
                    List<Country> country = new ArrayList<>();
                    if (resultCities == null) resultCities = new ArrayList<>();
                    for (CityCache cityCache : listCitiesCash) {
                        areaNames.add(0, new AreaName(cityCache.cityName));
                        country.add(0, new Country(cityCache.country));
                        resultCities.add(i, new City(areaNames, country));
                        i++;
                    }
                    Log.d(TAG, "getCitiesFromDB: что-то удалось ---------");
                    if (resultCities.size() == 0) {
//TODO
                    }
                    getViewState().updateRecyclerView();
                    getViewState().progressBarSetVisibility(View.INVISIBLE);
                }, throwable -> {
                    Log.d(TAG, "getWeatherFromDB: не удалось");
                    getViewState().progressBarSetVisibility(View.INVISIBLE);
                    //TODO нет интернет соединения, нет сохраненных данных

                });
    }

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
            if (v != null && resultCities != null )
                getViewState().onClick(v, resultCities.get(position).areaName.get(0).value);
            else
                Log.d(TAG, "onClick: error");
        }
    }

}
