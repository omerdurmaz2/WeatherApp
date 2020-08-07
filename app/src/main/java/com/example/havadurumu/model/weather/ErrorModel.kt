package com.example.havadurumu.model.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ErrorModel : Serializable {
    @SerializedName("code ")
    var code : Int? = null

    @SerializedName("error")
    var message: String? = null
}