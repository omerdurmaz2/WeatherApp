package com.example.havadurumu.model.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class WeatherResponse : Serializable {
    @SerializedName("data")
    var data: List<WeatherDataModel>? = null

    @SerializedName("city_name")
    var city_name: String? = null

    @SerializedName("lon")
    var lon: String? = null

    @SerializedName("timezone")
    var timezone: String? = null

    @SerializedName("country_code")
    var lat: String? = null

    @SerializedName("state_code")
    var state_code: String? = null
}




