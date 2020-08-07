package com.example.havadurumu.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.havadurumu.R
import com.example.havadurumu.WeatherApp
import com.example.havadurumu.model.weather.WeatherDataModel
import com.example.havadurumu.services.WeatherApi
import kotlinx.android.synthetic.main.item_weather_of_day.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.round

class WeatherDaysAdapter(
    val context: Context,
    private val weatherList: List<WeatherDataModel?>?,
    val clickListener: (WeatherDataModel) -> Unit
) :
    RecyclerView.Adapter<WeatherDaysAdapter.ViewHolder>() {

    @Suppress("DEPRECATION")
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(weather: WeatherDataModel, context: Context) {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(weather.datetime)


            Glide.with(context).load(WeatherApi.iconUrl + weather.weather?.icon + ".png")
                .into(itemView.ivWeatherImage)
            itemView.tvDayName.text = SimpleDateFormat("EEE", Locale.getDefault()).format(date)
            itemView.tvDayDegree.text = weather.temp?.let { round(it).toInt().toString() + "°" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_weather_of_day, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return weatherList!!.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position != weatherList?.size?.minus(1))
            weatherList?.get(position + 1)?.let { it1 ->
                holder.bind(it1, context)
            }


        holder.itemView.setOnClickListener {
            weatherList?.get(position)?.let { it1 -> clickListener(it1) }
        }
    }
}