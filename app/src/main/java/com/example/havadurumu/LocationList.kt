package com.example.havadurumu

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class LocationList : RealmObject(){
    private var latitude: String? = null
    private var longitude: String? = null
    private var cityName: String? = null

    fun getLatitude(): String? {
        return latitude
    }

    fun getLongitude(): String? {
        return longitude
    }

    fun getCityName(): String? {
        return cityName
    }

    fun setLatitude(latitude: String) {
        this.latitude = latitude
    }

    fun setLongitude(longitude: String) {
        this.longitude = longitude
    }

    fun setCityName(cityName: String) {
        this.cityName = cityName
    }

    override fun toString(): String {
        return "LocationList(latitude=$latitude, longitude=$longitude, cityName=$cityName)"
    }


}