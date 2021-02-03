package com.example.mapweather.netWork

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Clouds(@SerializedName("all") @Expose val all: Int)

data class ForecastMain(
    @SerializedName("temp") @Expose val temp: Double,
    @SerializedName("pressure") @Expose val pressure: Double,
    @SerializedName("humidity") @Expose val humidity: Int,
    @SerializedName("temp_min") @Expose val tempMin: Double,
    @SerializedName("temp_max") @Expose val tempMax: Double
)

data class WeatherSys(
    @SerializedName("type") @Expose val type: Int,
    @SerializedName("id") @Expose var id: Int,
    @SerializedName("message") @Expose var message: Double,
    @SerializedName("country") @Expose var country: String,
    @SerializedName("sunrise") @Expose var sunrise: Int,
    @SerializedName("sunset") @Expose var sunset: Int
)


data class Wind(
    @SerializedName("speed") @Expose val speed: Double,
    @SerializedName("deg") @Expose val deg: Double
)

data class WeatherData(
    @SerializedName("coord") @Expose val coord: Coord,
    @SerializedName("weather") @Expose val weather: ArrayList<Weather>,
    @SerializedName("base") @Expose val base: String,
    @SerializedName("main") @Expose val main: ForecastMain,
    @SerializedName("visibility") @Expose val visibility: Int,
    @SerializedName("wind") @Expose val wind: Wind,
    @SerializedName("clouds") @Expose val clouds: Clouds,
    @SerializedName("dt") @Expose val dt: Double,
    @SerializedName("sys") @Expose val sys: WeatherSys,
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("cod") @Expose val cod: Int
)
