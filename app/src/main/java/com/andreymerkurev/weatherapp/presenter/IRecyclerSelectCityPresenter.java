package com.andreymerkurev.weatherapp.presenter;

import android.view.View;

import com.andreymerkurev.weatherapp.view.IViewHolder;

public interface IRecyclerSelectCityPresenter {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();
    void onClick(View v, int position);
}
