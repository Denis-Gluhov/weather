package denis.ru.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import denis.ru.weather.R;
import denis.ru.weather.model.Forecast;
import denis.ru.weather.model.Forecastday_;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    private List<Forecastday_> forecastdayList;
    private LayoutInflater inflater;
    private Context context;

    @Inject
    public ForecastAdapter(@NonNull Context context,
                           @NonNull LayoutInflater layoutInflater) {
        this.context = context;
        inflater = layoutInflater;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
//        if (forecastdayList != null && forecastdayList.size() > 0) {
//            Forecastday_ forecastday = forecastdayList.get(position);
//            holder.dateForecast.setText(forecastday.getDate().getDay() + " " +
//                    forecastday.getDate().getMonthname() + " " + forecastday.getDate().getYear());
//            holder.weekDay.setText(forecastday.getDate().getWeekday());
//            holder.temperHigh.setText("High : " + forecastday.getHigh().getCelsius() +
//                    " C (" + forecastday.getHigh().getFahrenheit() + " F)");
//            holder.temperLow.setText("Low : " + forecastday.getLow().getCelsius() +
//                    " C (" + forecastday.getLow().getFahrenheit() + " F)");
//            holder.conditions.setText(forecastday.getConditions());
//            holder.setImage(forecastday.getIconUrl());
//        }
    }

    public void setData(Forecast data) {
        forecastdayList = data.getSimpleforecast().getForecastday();
    }

    @Override
    public int getItemCount() {
        if (forecastdayList == null)
            return 0;
        else return forecastdayList.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder{

        CardView cardForecast;
        TextView dateForecast, weekDay, temperHigh, temperLow, conditions;
        ImageView imageForecast;

        ForecastViewHolder(View itemView) {
            super(itemView);
            cardForecast = (CardView) itemView.findViewById(R.id.card_forecast_current);
            dateForecast = (TextView) itemView.findViewById(R.id.date_forecast);
            weekDay = (TextView) itemView.findViewById(R.id.title_forecast);
            temperHigh = (TextView) itemView.findViewById(R.id.temper_high);
            temperLow = (TextView) itemView.findViewById(R.id.temper_low);
            conditions = (TextView) itemView.findViewById(R.id.conditions);
            imageForecast = (ImageView) itemView.findViewById(R.id.image_forecast);
        }

        void setImage(String url){
            Picasso.with(context)
                    .load(url)
                    .into(imageForecast);
        }
    }
}
