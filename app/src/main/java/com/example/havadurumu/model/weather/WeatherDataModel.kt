package com.example.havadurumu.model.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherDataModel : Serializable {

    @SerializedName("varid_date")
    var varid_date: String? = null

    @SerializedName("ts")
    var ts: Double? = null

    @SerializedName("datetime")
    var datetime: String? = null

    @SerializedName("wind_gust_spd")
    var wind_gust_spd: Double? = null

    @SerializedName("wind_spd")
    var wind_spd: Double? = null

    @SerializedName("windCdir")
    var windCdir: Double? = null

    @SerializedName("wind_cdir")
    var wind_cdir: String? = null

    @SerializedName("wind_cdir_full")
    var wind_cdir_full: String? = null

    @SerializedName("cityName")
    var cityName: String? = null

    @SerializedName("temp")
    var temp: Double? = null

    @SerializedName("max_temp")
    var max_temp: Double? = null

    @SerializedName("min_temp")
    var min_temp: Double? = null

    @SerializedName("high_temp")
    var high_temp: Double? = null

    @SerializedName("low_temp")
    var low_temp: Double? = null

    @SerializedName("app_max_temp")
    var app_max_temp: Double? = null

    @SerializedName("app_min_temp")
    var app_min_temp: Double? = null

    @SerializedName("pop")
    var pop: Double? = null

    @SerializedName("precip")
    var precip: Double? = null

    @SerializedName("snow")
    var snow: Double? = null

    @SerializedName("snow_depth")
    var snow_depth: Double? = null

    @SerializedName("slp")
    var slp: Double? = null

    @SerializedName("pres")
    var pres: Double? = null

    @SerializedName("dewpt")
    var dewpt: Double? = null

    @SerializedName("rh")
    var rh: Double? = null

    @SerializedName("weather")
    var weather: Weather? = null

    @SerializedName("pod")
    var pod: String? = null

    @SerializedName("clouds_low")
    var clouds_low: Double? = null

    @SerializedName("clouds_mid")
    var clouds_mid: String? = null

    @SerializedName("clouds_hi")
    var clouds_hi: Double? = null

    @SerializedName("clouds")
    var clouds: Double? = null

    @SerializedName("vis")
    var vis: Double? = null

    @SerializedName("max_dhi")
    var max_dhi: Double? = null

    @SerializedName("uv")
    var uv: Double? = null

    @SerializedName("moon_phase")
    var moon_phase: Double? = null

    @SerializedName("moon_phase_lunation")
    var moon_phase_lunation: Double? = null

    @SerializedName("moonrise_ts")
    var moonrise_ts: Double? = null

    @SerializedName("moonset_ts")
    var moonset_ts: Double? = null

    @SerializedName("sunrise_ts")
    var sunrise_ts: Double? = null

    @SerializedName("sunset_ts")
    var sunset_ts: Double? = null
}