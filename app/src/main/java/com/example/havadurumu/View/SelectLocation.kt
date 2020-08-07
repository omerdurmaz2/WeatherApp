package com.example.havadurumu.View

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.havadurumu.R
import com.example.havadurumu.LocationList
import com.example.havadurumu.model.adapters.CountryAdapter
import com.example.havadurumu.model.adapters.DistrictAdapter
import com.example.havadurumu.model.adapters.RegionAdapter
import com.example.havadurumu.model.countries.DistrictResponse
import com.example.havadurumu.model.countries.CountryResponse
import com.example.havadurumu.model.countries.RegionResponse
import com.example.havadurumu.services.CountryApi
import com.example.havadurumu.services.NetworkCallback
import com.example.havadurumu.services.RestControllerFactory
import io.realm.Realm
import io.realm.RealmResults
import retrofit2.Call
import retrofit2.Response
import java.util.*

class SelectLocation : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var spCountry: Spinner
    private lateinit var spRegion: Spinner
    private lateinit var spDistrict: Spinner
    private lateinit var countryList: List<CountryResponse>
    private lateinit var regionList: List<RegionResponse>
    private lateinit var districtList: List<DistrictResponse>
    private lateinit var countrCode: String
    private lateinit var regionName: String
    private lateinit var districtLatitude: String
    private lateinit var districtLongitude: String


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_location)
        defineObjects()

        val createNew: Boolean? = intent.extras?.getBoolean("new")
        if (createNew != true) {
            loadLanguage()
            checkLocations()
        }

        pullCountries()
        onSpinnerClickListener()

    }





    private fun defineObjects() {
        realm = Realm.getDefaultInstance()
        spCountry = findViewById(R.id.spCountry)
        spRegion = findViewById(R.id.spRegion)
        spDistrict = findViewById(R.id.spDistrict)

    }

    private fun checkLocations() {
        realm.beginTransaction();
        val result: RealmResults<LocationList> = realm.where(
            LocationList::class.java
        ).findAll()
        if (result.size > 0) {
            realm.commitTransaction();
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {

        }
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setLanguage(lang: String?) {
        var locale = Locale(lang)
        Locale.setDefault(locale)
        baseContext.resources.configuration.setLocale(locale)
        var editor: SharedPreferences.Editor =
            getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit()
        editor.putString("appLanguage", lang)
        editor.apply()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun loadLanguage() {
        var prefs: SharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        var lang: String? = prefs.getString("appLanguage", "")
        if (lang != null) setLanguage(lang)
        else setLanguage(Locale.getDefault().toLanguageTag())
    }


    private fun pullCountries() {


        RestControllerFactory.instance.getCountryFactory()
            .getCountryList(CountryApi.key)
            .enqueue(object : NetworkCallback<List<CountryResponse>>() {
                override fun onResponse(
                    call: Call<List<CountryResponse>>,
                    response: Response<List<CountryResponse>>
                ) {
                    if (response.isSuccessful) {

                        countryList = response.body()
                        Log.i("sss", "" + response.body())
                        val countryAdapter =
                            CountryAdapter(context = applicationContext, list = response.body())
                        spCountry.adapter = countryAdapter

                    }
                }

                override fun onFailure(
                    call: Call<List<CountryResponse>>,
                    t: Throwable
                ) {
                    Log.i("sss", "Hata: " + t.localizedMessage + "\n" + t.message)
                }
            })
    }

    private fun onSpinnerClickListener() {
        spCountry?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                countrCode = countryList[position].code.toString()
                pullRegions(countryList[position].code)
            }

        }
        spRegion?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                regionName = regionList[position].region.toString()
                pullDistricts()
            }

        }
    }

    private fun pullRegions(cCode: String?) {

        RestControllerFactory.instance.getCountryFactory()
            .getRegionList(cCode, CountryApi.key)
            .enqueue(object : NetworkCallback<List<RegionResponse>>() {
                override fun onResponse(
                    call: Call<List<RegionResponse>>,
                    response: Response<List<RegionResponse>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("sss", "Başarılı! " + response.body())
                        regionList = response.body()
                        val regionAdapter =
                            RegionAdapter(context = applicationContext, list = response.body())
                        spRegion.adapter = regionAdapter

                    }
                }

                override fun onFailure(
                    call: Call<List<RegionResponse>>,
                    t: Throwable
                ) {
                    Log.i("sss", "Hata: " + t.localizedMessage + "\n" + t.message)
                }
            })
    }

    private fun pullDistricts() {

        RestControllerFactory.instance.getCountryFactory()
            .getDistrictList(countrCode, regionName, CountryApi.key)
            .enqueue(object : NetworkCallback<List<DistrictResponse>>() {
                override fun onResponse(
                    call: Call<List<DistrictResponse>>,
                    response: Response<List<DistrictResponse>>
                ) {
                    if (response.isSuccessful) {
                        val districtAdapter =
                            DistrictAdapter(context = applicationContext, list = response.body())
                        spRegion.adapter = districtAdapter
                        Log.i("sss", "Başarılı! " + response.body())
                    }
                }

                override fun onFailure(
                    call: Call<List<DistrictResponse>>,
                    t: Throwable
                ) {
                    Log.i("sss", "Hata: " + t.localizedMessage + "\n" + t.message)
                }
            })
    }
}