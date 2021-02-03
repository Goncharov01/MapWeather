package com.example.mapweather.netWork

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("coord") @Expose val coord: Coord,
    @SerializedName("county") @Expose val county: String
)

data class ForecastData(
    @SerializedName("cod") @Expose val cod: String,
    @SerializedName("message") @Expose val message: Double,
    @SerializedName("cnt") @Expose val cnt: Double,
    @SerializedName("list") @Expose val list: ArrayList<List>,
    @SerializedName("city") @Expose val city: City
)

data class Temp(val day: Double, val min: Double, val max: Double, val night: Double, val eve: Double, val morn: Double)

data class Rain(@SerializedName("3h") @Expose val _3h: Double)

data class Snow(@SerializedName("3h") @Expose val _3h: Double)

data class ForecastWind(
    @SerializedName("speed") @Expose val speed: Double,
    @SerializedName("deg") @Expose val deg: Double
)

data class List(
    @SerializedName("dt") @Expose val dt: Int,
    @SerializedName("main") @Expose val main: Main,
    @SerializedName("weather") @Expose val weather: ArrayList<Weather>,
    @SerializedName("clouds") @Expose val clouds: Clouds,
    @SerializedName("wind") @Expose val wind: ForecastWind,
    @SerializedName("snow") @Expose val snow: Snow,
    @SerializedName("sys") @Expose val sys: Sys,
    @SerializedName("dt_txt") @Expose val dtTxt: String,
    @SerializedName("rain") @Expose val rain: Rain
)

data class Main(
    @SerializedName("temp") @Expose val temp: Double,
    @SerializedName("temp_min") @Expose val tempMin: Double,
    @SerializedName("temp_max") @Expose val tempMax: Double,
    @SerializedName("pressure") @Expose val pressure: Double,
    @SerializedName("sea_level") @Expose val seaLevel: Double,
    @SerializedName("grnd_level") @Expose val grndLevel: Double,
    @SerializedName("humidity") @Expose val humidity: Double,
    @SerializedName("temp_kf") @Expose val tempKf: Double
)

data class Sys(@SerializedName("pod") @Expose val pod: String)



