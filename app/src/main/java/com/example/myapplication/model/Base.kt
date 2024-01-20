package com.example.myapplication.model

import java.io.Serializable

data class Base(
    val dt: Long,
    val main: Main,
    val weather: List<WeatherDescription>,
    val visibility: Long,
    val wind : Wind,
    val dt_txt : String
) : Serializable