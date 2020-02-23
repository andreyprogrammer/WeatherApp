package com.andreymerkurev.weatherapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andreymerkurev.weatherapp.R;
import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.presenter.SelectCityPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SelectCityActivity extends MvpAppCompatActivity implements ISelectCityView {
    private static final String TAG = "app_log - WeatherActivity";
    private Button btn;
    private RecyclerAdapter adapter;
    private ProgressBar progressBar;
    private EditText editText;

    @InjectPresenter
    SelectCityPresenter selectCityPresenter;

    @ProvidePresenter
    public SelectCityPresenter provideSelectCityPresenter() {
        return new SelectCityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        App.getAppComponent().inject(this);
        progressBar = findViewById(R.id.pb_select_city);
        editText = findViewById(R.id.et_select_city);
        Toolbar toolbar = findViewById(R.id.toolbar_select_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initRecyclerView();
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
//            Log.d(TAG, "entry act");
//            selectCityPresenter.getAllCitiesFromInternet(); //TODO проверка на пустое поле
            selectCityPresenter.getAllCitiesFromInternet(editText.getText().toString());

        });


    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(selectCityPresenter.getRecyclerSelectCityPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v, String cityName) {
        Intent intent = new Intent(v.getContext(), WeatherActivity.class);
        intent.putExtra("CITY_NAME", cityName);
        startActivity(intent);
    }

    @Override
    public void progressBarSetVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
