package com.andreymerkurev.weatherapp.app;

import javax.inject.Singleton;

import com.andreymerkurev.weatherapp.presenter.MainPresenter;
import com.andreymerkurev.weatherapp.presenter.SelectCityPresenter;
import com.andreymerkurev.weatherapp.view.MainActivity;
import com.andreymerkurev.weatherapp.view.RecyclerAdapter;
import com.andreymerkurev.weatherapp.view.SelectCityActivity;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
    void inject(MainActivity mainActivity);
    void inject(SelectCityPresenter selectCityPresenter);
    void inject(RecyclerAdapter recyclerViewAdapter);
    void inject(SelectCityActivity selectCityActivity);
}