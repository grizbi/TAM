package com.example.myapplication.repository

import com.example.myapplication.model.Weather
import retrofit2.Response

class WeatherRepository {
    suspend fun getWeather(): Response<Weather> = WeatherApi.weatherApi.getWeather()
}