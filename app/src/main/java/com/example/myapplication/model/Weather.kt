package com.example.myapplication.model

import java.io.Serializable

data class Weather (
    val list: List<Base>,
    val city: City
) : Serializable