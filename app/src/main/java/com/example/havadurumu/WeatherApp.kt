package com.example.havadurumu

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class WeatherApp : Application() {
    companion object {
        private lateinit var mContext: Context

        fun getApplicationContext(): Context {
            return mContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("localeVariable").build()
        Realm.setDefaultConfiguration(config)
        initializeApi()
    }

    private fun initializeApi() {
        mContext = applicationContext
    }
}