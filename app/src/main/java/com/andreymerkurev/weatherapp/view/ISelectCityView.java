package com.andreymerkurev.weatherapp.view;

import android.view.View;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = SkipStrategy.class)
public interface ISelectCityView extends MvpView {
    void updateRecyclerView();
    void onClick(View v, String cityName);
    void progressBarSetVisibility(int visibility);
}
