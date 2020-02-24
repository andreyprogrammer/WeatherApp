package com.andreymerkurev.weatherapp.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.andreymerkurev.weatherapp.R;
import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.model.PicassoLoader;
import com.andreymerkurev.weatherapp.model.entity.CurrentCondition;
import com.andreymerkurev.weatherapp.presenter.WeatherPresenter;

import javax.inject.Inject;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class WeatherActivity extends MvpAppCompatActivity implements IWeatherView {
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private TextView tvCityName;
    private TextView tvTemp;
    private ImageView imageView;
    private TextView tvWeather;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvWind;

    @InjectPresenter
    WeatherPresenter weatherPresenter;

    @ProvidePresenter
    public WeatherPresenter provideMainPresenter() {
        return new WeatherPresenter();
    }

    @Inject
    PicassoLoader picassoLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        App.getAppComponent().inject(this);
        initViews();
        String cityName = getIntent().getStringExtra("CITY_NAME");
        if (cityName.length() != 0) {
            weatherPresenter.getWeatherFromInternet(cityName);
        } else {
                tvCityName.setText(R.string.no_connection);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    void initViews() {
        toolbar = findViewById(R.id.toolbar_main);
        progressBar = findViewById(R.id.pb_main);
        setSupportActionBar(toolbar);
        tvCityName = findViewById(R.id.tv_city_name);
        tvTemp = findViewById(R.id.tv_temp);
        tvWeather = findViewById(R.id.tv_weather);
        imageView = findViewById(R.id.iv);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvPressure = findViewById(R.id.tv_pressure);
        tvWind = findViewById(R.id.tv_wind);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search) {
//            Intent intent = new Intent(this, SelectCityActivity.class);
//            startActivity(intent);
            onBackPressed();
        }
        return true;
    }

    @Override
    public void progressBarSetVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setDescriptions(CurrentCondition currentCondition) {
        tvCityName.setText(currentCondition.request.get(0).query);
        tvTemp.setText(currentCondition.currentCondition.get(0).temp);
        tvWeather.setText(currentCondition.currentCondition.get(0).lang.get(0).value);
        tvHumidity.setText(currentCondition.currentCondition.get(0).humidity);
        tvPressure.setText(currentCondition.currentCondition.get(0).pressure);
        tvWind.setText(currentCondition.currentCondition.get(0).windspeed);
        setImage(currentCondition.currentCondition.get(0).weatherIconUrl.get(0).value, imageView);
    }

    public void setImage(String url, ImageView imageView) {
        picassoLoader.loadImage(url, imageView);
    }
}
