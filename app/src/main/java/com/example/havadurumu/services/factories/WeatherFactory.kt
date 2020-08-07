package com.example.havadurumu.services.factories

import retrofit2.http.GET
import com.example.havadurumu.model.weather.WeatherResponse
import retrofit2.Call
import retrofit2.http.Query

interface WeatherFactory {

    @GET("daily")
    fun getWeather(
        @Query("key") key: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("lang") lang: String = "tr",
        @Query("units") units: String = "M",
        @Query("days") days: Int = 10
    ): Call<WeatherResponse>


}