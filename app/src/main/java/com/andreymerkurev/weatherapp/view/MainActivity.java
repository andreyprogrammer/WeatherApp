package com.andreymerkurev.weatherapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.andreymerkurev.weatherapp.R;
import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.presenter.MainPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements IMainView {
    private Toolbar toolbar;

    @InjectPresenter
    MainPresenter mainPresenter;

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getAppComponent().inject(this);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(this, SelectCityActivity.class);
            startActivity(intent);
        }
        return true;
    }





}
