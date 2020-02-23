package com.andreymerkurev.weatherapp.app;

import javax.inject.Singleton;
import com.andreymerkurev.weatherapp.presenter.WeatherPresenter;
import com.andreymerkurev.weatherapp.presenter.SelectCityPresenter;
import com.andreymerkurev.weatherapp.view.WeatherActivity;
import com.andreymerkurev.weatherapp.view.RecyclerAdapter;
import com.andreymerkurev.weatherapp.view.SelectCityActivity;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(WeatherPresenter weatherPresenter);
    void inject(WeatherActivity weatherActivity);
    void inject(SelectCityPresenter selectCityPresenter);
    void inject(SelectCityActivity selectCityActivity);
    void inject(RecyclerAdapter recyclerViewAdapter);
}