package com.example.havadurumu.services.factories

import com.example.havadurumu.model.countries.DistrictResponse
import com.example.havadurumu.model.countries.CountryResponse
import com.example.havadurumu.model.countries.RegionResponse
import com.example.havadurumu.services.CountryApi
import retrofit2.Call
import retrofit2.http.*

interface CountryFactory {


    @GET("country/all/")
    fun getCountryList(
        @Query("key") key: String? = CountryApi.key
    ): Call<List<CountryResponse>>


    @GET("region/{COUNTRY_CODE}/all/")
    fun getRegionList(
        @Path("COUNTRY_CODE") countryCode: String? = "tr",
        @Query("key") key: String? = CountryApi.key
    ): Call<List<RegionResponse>>

    @GET("city/{COUNTRY_CODE}/search/")
    fun getDistrictList(
        @Path("COUNTRY_CODE") countryCode: String? = "tr",
        @Query("region") region: String? = CountryApi.key,
        @Query("key") key: String? = CountryApi.key
    ): Call<List<DistrictResponse>>

}