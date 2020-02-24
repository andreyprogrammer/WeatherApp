package com.andreymerkurev.weatherapp.view;

import com.andreymerkurev.weatherapp.model.entity.CurrentCondition;
import com.andreymerkurev.weatherapp.model.entity.Weather;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = SkipStrategy.class)
public interface IWeatherView extends MvpView {
    void progressBarSetVisibility(int visibility);
    void setDescriptions(CurrentCondition currentCondition, Boolean isDataFromDB);
    void showNoConnection();
}
