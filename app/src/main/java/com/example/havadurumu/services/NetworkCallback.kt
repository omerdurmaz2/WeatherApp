package com.example.havadurumu.services

import android.content.Intent
import android.widget.Toast
import com.example.havadurumu.WeatherApp
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import com.example.havadurumu.R

abstract class NetworkCallback<T> : retrofit2.Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() == 401 && response.message() == "Unauthorized") {
            Toast.makeText(

                WeatherApp.getApplicationContext(),
                R.string.technical_error_message,
                Toast.LENGTH_LONG
            )
        }
        if (response.code() != 200) {
            if (response.errorBody() != null) {
                try {
                    val errorBodyContent = JSONObject(response.errorBody()!!.string())
                    Toast.makeText(
                        WeatherApp.getApplicationContext(),
                        errorBodyContent.getString("resultMessage"),
                        Toast.LENGTH_LONG
                    )
                        .show()
                } catch (e: Exception) {
                    Toast.makeText(
                        WeatherApp.getApplicationContext(),
                        R.string.technical_error_message,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t.message!!.contains("Unable to resolve host")) {
            Toast.makeText(
                WeatherApp.getApplicationContext(),
                R.string.error_internet_connection,
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            Toast.makeText(
                WeatherApp.getApplicationContext(),
                t.localizedMessage, Toast.LENGTH_LONG
            )
                .show()
        }
    }
}