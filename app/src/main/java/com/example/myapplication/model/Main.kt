package com.example.myapplication.model

import java.io.Serializable

data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val humidity: Long
) : Serializable