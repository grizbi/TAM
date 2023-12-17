package com.example.myapplication.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository()

    val liveWeatherData = MutableLiveData<UiState<Weather>>()

    fun getData() {
        liveWeatherData.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val request = weatherRepository.getWeather()
                Log.d("MainViewModel", "request return code: , ${request.code()}")

                if(request.isSuccessful) {
                    val weather = request.body()
                    liveWeatherData.postValue(UiState(data = weather))
                } else {
                    liveWeatherData.postValue(UiState(error = "${request.code()}"))
                    Log.d("MainViewModel", "Request failed, ${request.errorBody()}");
                }
            } catch (e: Exception) {
                liveWeatherData.postValue(UiState(error = "Exception $e"))
                Log.e("MainViewModel", "Request failed, exception ", e)
            }
        }
    }
}