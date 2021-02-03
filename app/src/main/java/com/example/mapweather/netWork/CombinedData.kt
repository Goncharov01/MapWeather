package com.example.mapweather.netWork

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CombinedData(val weatherData: WeatherData, val forecastData: ForecastData)

data class Coord(
    @SerializedName("lon") @Expose val lon: Double,
    @SerializedName("lat") @Expose val lat: Double
)

data class Weather(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("main") @Expose val main: String,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("icon") @Expose val icon: String
)
