package com.example.mapweather

import com.example.mapweather.netWork.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdapterRetrofit {

    fun getRetrofitAdapter(): WeatherService {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherService::class.java)

    }
}