package com.example.mapweather.netWork

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(Constants.API_WEATHER + Constants.API_APP_ID)
    fun getWeatherByCity(
        @Query("q") cityName: String, @Query("appid") appId: String
    ): Call<WeatherData>

//    const val API_BASE_URL = "http://api.openweathermap.org/data/2.5/"
//    const val API_WEATHER = "weather?"

    @GET(Constants.API_WEATHER + Constants.API_APP_ID)
    fun getWeatherByLatLon(
        @Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appId: String
    ): Call<WeatherData>

}