package com.example.havadurumu.View

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.havadurumu.R
import com.example.havadurumu.LocationList
import com.example.havadurumu.model.weather.WeatherDataModel
import com.example.havadurumu.model.weather.WeatherResponse
import com.example.havadurumu.services.NetworkCallback
import com.example.havadurumu.services.RestControllerFactory
import com.example.havadurumu.services.WeatherApi
import io.realm.Realm
import io.realm.RealmResults
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.annotation.meta.When
import kotlin.collections.ArrayList
import kotlin.math.floor
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var weatherDegree: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var relativeLayout: RelativeLayout
    private lateinit var backgroundWallpaper: BackgroundWallpaper
    private lateinit var tvCurrentDayText: TextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadLanguage()
        defineObjects()
        //checkLocations()
        pullWeather("40.8805267", "29.2901824")
        backgroundWallpaper = BackgroundWallpaper()
        relativeLayout.setBackgroundResource(backgroundWallpaper.randomBackGround())
        setDayText()


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun loadLanguage() {
        val prefs: SharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val lang: String? = prefs.getString("appLanguage", "")
        if (lang != null) setLanguage(lang)
        else setLanguage(Locale.getDefault().toLanguageTag())
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setLanguage(lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        baseContext.resources.configuration.setLocale(locale)
        val editor: SharedPreferences.Editor =
            getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit()
        editor.putString("appLanguage", lang)
        editor.apply()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDayText() {
        tvCurrentDayText.text = LocalDate.now().dayOfWeek.name
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        //checkLocations()
        super.onResume()
        backgroundWallpaper = BackgroundWallpaper()
        relativeLayout.setBackgroundResource(backgroundWallpaper.randomBackGround())

    }

    private fun defineObjects() {
        realm = Realm.getDefaultInstance()
        weatherDegree = findViewById(R.id.tvWeatherDegree)
        recyclerView = findViewById(R.id.rvListOfDays)
        relativeLayout = findViewById(R.id.rvBackGround)
        tvCurrentDayText = findViewById(R.id.tvDayText)
    }


    private fun checkLocations() {
        realm.beginTransaction()
        val result: RealmResults<LocationList> = realm.where(
            LocationList::class.java
        ).findAll()
        if (result.size <= 0) {
            realm.commitTransaction()
            val intent = Intent(this, SelectLocation::class.java)
            startActivity(intent)
        } else {
            realm.commitTransaction()
        }
    }


    private fun pullWeather(lat: String, lon: String) {
        RestControllerFactory.instance.getWeatherFactory()
            .getWeather(WeatherApi.key, lat, lon)
            .enqueue(object : NetworkCallback<WeatherResponse>() {
                @SuppressLint("SetTextI18n", "ShowToast")
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {

                    if (response.isSuccessful) {
                        if (response.body().data?.count() != 0) {
                            Log.i("sss", "Başarılı! " + response.body().data)
                            weatherDegree.text =
                                response.body().data?.get(0)?.temp?.let {
                                    round(it).toInt().toString()
                                } + "°"


                            val daysAdapter =
                                WeatherDaysAdapter(applicationContext, response.body().data) {

                                }
                            recyclerView.layoutManager = LinearLayoutManager(
                                applicationContext,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            recyclerView.adapter = daysAdapter


                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Bugünlük bedava hava durumu limitini aştık. Lütfen yarın tekrar dene :)",
                            Toast.LENGTH_SHORT
                        )
                        Log.i("sss", "Hata: " + response.errorBody())

                        var weatherList: ArrayList<WeatherDataModel> = arrayListOf()

                        var weatherDataModel1 = WeatherDataModel()
                        weatherDataModel1.temp = 42.0
                        weatherDataModel1.datetime = "2019-08-05:17"

                        var weatherDataModel2 = WeatherDataModel()
                        weatherDataModel2.temp = 25.0
                        weatherDataModel2.datetime = "2019-08-05:17"

                        var weatherDataModel3 = WeatherDataModel()
                        weatherDataModel3.temp = 27.0
                        weatherDataModel3.datetime = "2019-08-05:17"

                        var weatherDataModel4 = WeatherDataModel()
                        weatherDataModel4.temp = 27.0
                        weatherDataModel4.datetime = "2019-08-05:17"

                        var weatherDataModel5 = WeatherDataModel()
                        weatherDataModel5.temp = 27.0
                        weatherDataModel5.datetime = "2019-08-05:17"

                        var weatherDataModel6 = WeatherDataModel()
                        weatherDataModel6.temp = 27.0
                        weatherDataModel6.datetime = "2019-08-05:17"

                        var weatherDataModel7 = WeatherDataModel()
                        weatherDataModel7.temp = 27.0
                        weatherDataModel7.datetime = "2019-08-05:17"

                        weatherList.add(weatherDataModel1)
                        weatherList.add(weatherDataModel2)
                        weatherList.add(weatherDataModel3)
                        weatherList.add(weatherDataModel4)
                        weatherList.add(weatherDataModel5)
                        weatherList.add(weatherDataModel6)
                        weatherList.add(weatherDataModel7)

                        val daysAdapter =
                            WeatherDaysAdapter(applicationContext, weatherList) {

                            }
                        recyclerView.layoutManager = LinearLayoutManager(
                            applicationContext,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        recyclerView.adapter = daysAdapter
                    }

                }

                override fun onFailure(
                    call: Call<WeatherResponse>,
                    t: Throwable
                ) {
                    Log.i("sss", "Hata: " + t.localizedMessage + "\n" + t.message)
                }
            })
    }


}





