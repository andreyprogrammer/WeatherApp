package com.andreymerkurev.weatherapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import javax.inject.Inject;
import com.andreymerkurev.weatherapp.R;
import com.andreymerkurev.weatherapp.app.App;
import com.andreymerkurev.weatherapp.model.PicassoLoader;
import com.andreymerkurev.weatherapp.presenter.IRecyclerSelectCityPresenter;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.InnerViewHolder>{
    private IRecyclerSelectCityPresenter iRecyclerSelectCityPresenter;

    @Inject
    PicassoLoader picassoLoader; //TODO not use

    RecyclerAdapter(IRecyclerSelectCityPresenter iRecyclerSelectCityPresenter) {
        App.getAppComponent().inject(this);
        this.iRecyclerSelectCityPresenter = iRecyclerSelectCityPresenter;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        holder.position = position;
        iRecyclerSelectCityPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerSelectCityPresenter.getItemCount();
    }

    class InnerViewHolder extends RecyclerView.ViewHolder implements IViewHolder {
        private ImageView imageView;
        private TextView tvName;
        private TextView tvCountry;
//        private TextView tvTemp;
        private int position = 0;

        InnerViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.card_item_name);
            tvCountry = itemView.findViewById(R.id.card_item_country);

//                imageView.setOnClickListener(view1 -> {
//                    iRecyclerSelectCityPresenter.onClick(view1, position);
//                });
            }

//        @Override
//        public void setWeather(String name, ) {
//
////            picassoLoader.loadImage(url, imageView);
//
//
//        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setWeather(String name, String country, String lat, String lon) {
            tvName.setText(name);
            tvCountry.setText(country);
        }
    }
}
