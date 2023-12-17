package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Base
import com.example.myapplication.model.MainViewModel
import com.example.myapplication.model.UiState
import com.example.myapplication.model.Weather
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(viewModel = viewModel)
                }
            }
        }
    }
}


@Composable
fun MainView(viewModel: MainViewModel) {
    val uiState by viewModel.liveWeatherData.observeAsState(UiState())

    when {
        uiState.isLoading -> {
            MyLoadingView()
        }

        uiState.error != null -> {
            ErrorView()
        }

        uiState.data != null -> {
            ListView(uiState.data!!)
        }
    }
}

@Composable
fun ListView(weather: Weather) {
    LazyColumn {
        items(weather.list) { item ->
            MyWeatherView(item, weather.city.name)
        }
    }
}

@Composable
fun MyLoadingView() {
    CircularProgressIndicator()
}

@Composable
fun ErrorView() {
    Text(
        text = "Could not get the response from the server",
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff82b1ff)
        ),
        modifier = Modifier
            .padding(top = 75.dp)
            .padding(start = 80.dp)

    )
}

fun getRandomImage(hour: String) =
    when (hour) {
        "01", "04", "22" -> R.drawable.night_weather
        "07" -> R.drawable.early_morning_weather
        "10" -> R.drawable.morning_weather
        "13", "16" -> R.drawable.weather
        "19" -> R.drawable.afternoon_weather
        else -> {
            R.drawable.morning_weather
        }
    }

@SuppressLint("SimpleDateFormat")
@Composable
fun MyWeatherView(weatherData: Base, city: String) {
    val feelsLike = weatherData.main.feels_like;
    val temperature = weatherData.main.temp;

    val timestamp = weatherData.dt * 1000

    val sdf = SimpleDateFormat("dd/MM/yy HH:mm")
    val netDate = Date(timestamp)
    val date = sdf.format(netDate)
    val hour = date.subSequence(9, 11).toString()
    val color = Color(0xFFF33A6A)


    println(hour)

    Box {
        Image(
            painter = painterResource(id = getRandomImage(hour)),
            contentDescription = "Weather",
            modifier = Modifier
                .size(235.dp)
        )
        Text(
            text = "Forecast",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            ),
            modifier = Modifier
                .padding(top = 60.dp)
                .padding(start = 80.dp)

        )
        Text(
            text = "City: $city",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            ),
            modifier = Modifier
                .padding(top = 75.dp)
                .padding(start = 80.dp)

        )
        Text(
            text = "Feels like: $feelsLike",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            ),
            modifier = Modifier
                .padding(top = 90.dp)
                .padding(start = 80.dp)

        )
        Text(
            text = "Temperature: $temperature",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            ),
            modifier = Modifier
                .padding(top = 105.dp)
                .padding(start = 80.dp)
        )
        Text(
            text = "Date: $date",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            ),
            modifier = Modifier
                .padding(top = 120.dp)
                .padding(start = 80.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun myAppPreview() {
    MyApplicationTheme {

        //mainView("Android")
    }
}