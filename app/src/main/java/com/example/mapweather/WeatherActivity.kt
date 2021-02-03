package com.example.mapweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapweather.netWork.Constants
import com.example.mapweather.netWork.WeatherAdapter
import com.example.mapweather.netWork.WeatherData
import com.example.mapweather.netWork.WeatherService
import kotlinx.android.synthetic.main.activity_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {

    lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        var location: Bundle = intent.getExtras()!!
        var lat: Double = location.getDouble("lat")
        var lon: Double = location.getDouble("lon")

        println("WeatherActivity ${lat}")
        println("WeatherActivity ${lon}")

        weatherService = AdapterRetrofit().getRetrofitAdapter()

        weatherService.getWeatherByLatLon(lat, lon, Constants.API_KEY)
            .enqueue(object : Callback<WeatherData> {
                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    println(t.message + "ERROR")
                }

                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {

                    var weatherAdapter = WeatherAdapter(this@WeatherActivity.applicationContext)

                    response.body()?.let { weatherAdapter.setDataWeather(it) }
                    weatherRecyclerView.adapter = weatherAdapter
                    weatherRecyclerView.layoutManager =
                        LinearLayoutManager(this@WeatherActivity.applicationContext)
                    weatherRecyclerView.setHasFixedSize(true)

                    println(response.body())

                }
            })

    }

}