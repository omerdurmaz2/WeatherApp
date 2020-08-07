package com.example.havadurumu.View

import android.os.Build
import android.util.Log
import android.util.TimeUtils
import androidx.annotation.RequiresApi
import com.example.havadurumu.R
import okhttp3.internal.format
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class BackgroundWallpaper() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun randomBackGround(): Int {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        val formatted = current.format(formatter).toInt()

        Log.i("sss", "Formatted time $formatted")


        val randomNumber = Random.nextInt(1, 4)
        return when (formatted) {
            in 0..6 -> getNight(randomNumber)
            in 6..11 -> getMorning(randomNumber)
            in 12..17 -> getNoon(randomNumber)
            in 17..21 -> getEvening(randomNumber)
            in 21..23 -> getNight(randomNumber)
            else -> R.drawable.noon_3
        }


    }

    private fun getMorning(number: Int): Int {
        return when (number) {
            1 -> R.drawable.morning_1
            2 -> R.drawable.morning_2
            3 -> R.drawable.morning_3
            4 -> R.drawable.morning_4
            else -> -1
        }

    }

    private fun getNoon(number: Int): Int {
        return when (number) {
            1 -> R.drawable.noon_1
            2 -> R.drawable.noon_2
            3 -> R.drawable.noon_3
            4 -> R.drawable.noon_4
            else -> -1
        }
    }

    private fun getEvening(number: Int): Int {
        return when (number) {
            1 -> R.drawable.evening_1
            2 -> R.drawable.evening_2
            3 -> R.drawable.evening_3
            4 -> R.drawable.evening_4
            else -> -1
        }
    }

    private fun getNight(number: Int): Int {
        return when (number) {
            1 -> R.drawable.night_1
            2 -> R.drawable.night_2
            3 -> R.drawable.night_3
            4 -> R.drawable.night4
            else -> -1
        }
    }
}


