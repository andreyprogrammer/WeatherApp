package com.andreymerkurev.weatherapp.model;

import android.widget.ImageView;
import com.andreymerkurev.weatherapp.R;
import com.squareup.picasso.Picasso;

public class PicassoLoader {
    public void loadImage(String url, ImageView imageView) {
        Picasso
                .get()
                .load(url)
                .error(R.drawable.smile)
                .into(imageView);
    }
}
