package com.example.havadurumu.services

import android.content.Context
import android.net.ConnectivityManager
import com.example.havadurumu.services.factories.CountryFactory
import com.example.havadurumu.services.factories.WeatherFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestControllerFactory {


    private var weatherFactory: WeatherFactory
    private var countryFactory: CountryFactory

    constructor() {

        val apiService: Retrofit = Retrofit.Builder()
            .baseUrl(WeatherApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()

        weatherFactory = apiService.create(WeatherFactory::class.java)


        val countryApiService: Retrofit = Retrofit.Builder()
            .baseUrl(CountryApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()

        countryFactory = countryApiService.create(CountryFactory::class.java)
    }

    companion object {
        val instance = RestControllerFactory()
        const val timeoutInterval = 60L
        var client = OkHttpClient()

        fun hasInternetConnection(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    }

    fun getWeatherFactory(): WeatherFactory {
        return weatherFactory
    }

    fun getCountryFactory(): CountryFactory {
        return countryFactory
    }
}