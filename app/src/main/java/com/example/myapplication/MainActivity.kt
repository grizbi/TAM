package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                    MainView(
                        viewModel = viewModel,
                        onClick = { id -> navigateToDetailsActivity(id) })
                }
            }
        }
    }

    private fun navigateToDetailsActivity(id: Base) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra("id", id)
        startActivity(detailsIntent)
    }
}


@Composable
fun MainView(viewModel: MainViewModel, onClick: (Base) -> Unit) {
    val uiState by viewModel.liveWeatherData.observeAsState(UiState())

    when {
        uiState.isLoading -> {
            MyLoadingView()
        }

        uiState.error != null -> {
            ErrorView()
        }

        uiState.data != null -> {
            ListView(uiState.data!!, onClick = { id -> onClick.invoke(id) })
        }
    }
}

@Composable
fun ListView(weather: Weather, onClick: (Base) -> Unit) {
    LazyColumn {
        items(weather.list) { item ->
            MyWeatherView(item, weather.city.name, onClick = { item -> onClick.invoke(item) })
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
        "00", "03", "21" -> R.drawable.night_weather
        "06" -> R.drawable.early_morning_weather
        "9" -> R.drawable.morning_weather
        "12", "15" -> R.drawable.weather
        "18" -> R.drawable.afternoon_weather
        else -> {
            R.drawable.morning_weather
        }
    }

@Composable
fun MyWeatherView(weatherData: Base, city: String, onClick: (Base) -> Unit) {
    val feelsLike = weatherData.main.feels_like;
    val temperature = weatherData.main.temp;

    val date = weatherData.dt_txt
    val hour = date.subSequence(11, 13).toString()
    val color = Color(0xffff8a65)

    Box(
        modifier = Modifier
            .clickable { onClick.invoke(weatherData) }
            .background(color = Color(0xffb3e5fc))
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = getRandomImage(hour)),
            contentDescription = "Weather",
            modifier = Modifier
                .size(350.dp, 200.dp)
                .padding(start = 40.dp)
        )
        Text(
            text = "Forecast",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = color
            ),
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(start = 150.dp)

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
                .padding(start = 150.dp)

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
                .padding(start = 150.dp)

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
                .padding(start = 150.dp)
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
                .padding(start = 150.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApplicationTheme {

        //mainView("Android")
    }
}