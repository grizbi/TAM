package com.example.myapplication.repository
import com.example.myapplication.model.Weather
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherApi {

    @GET("/data/2.5/forecast?q=Krakow&appid=52a28396c01d2d8a1ebfd5c556cbc63b&units=metric")
    suspend fun getWeather(): Response<Weather>

    companion object {
        private const val WEATHER_URL = "https://api.openweathermap.org"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val weatherApi: WeatherApi by lazy {
            retrofit.create(WeatherApi::class.java)
        }
    }
}