package com.example.havadurumu.model.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Weather : Serializable {

    @SerializedName("code")
    val code: String? = null

    @SerializedName("icon")
    val icon: String? = null

    @SerializedName("description")
    val description: String? = null
}


