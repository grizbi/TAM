package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Base
import com.example.myapplication.ui.theme.MyApplicationTheme

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getSerializableExtra("id") as Base

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailView(id)
                }
            }
        }

    }

    @Composable
    fun DetailView(id: Base) {
        val color = Color.DarkGray

        val fontSize = 24.sp
        Box {
            Image(
                painter = painterResource(R.drawable.detailsweather),
                contentDescription = "Weather",
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Cracow",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 100.dp)
                    .padding(start = 100.dp)

            )
            Text(
                text = "Description: ${id.weather[0].description}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 200.dp)
                    .padding(start = 60.dp)

            )
            Text(
                text = "Temperature: ${id.main.temp}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 240.dp)
                    .padding(start = 60.dp)

            )
            Text(
                text = "Feels like: ${id.main.feels_like}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 280.dp)
                    .padding(start = 60.dp)

            )
            Text(
                text = "Temperature max: ${id.main.temp_max}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 320.dp)
                    .padding(start = 60.dp)
            )
            Text(
                text = "Temperature min: ${id.main.temp_min}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 360.dp)
                    .padding(start = 60.dp)
            )
            Text(
                text = "Humidity: ${id.main.humidity}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 400.dp)
                    .padding(start = 60.dp)
            )
            Text(
                text = "Visibility: ${id.visibility}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 440.dp)
                    .padding(start = 60.dp)
            )
            Text(
                text = "Wind speed: ${id.wind.speed}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 480.dp)
                    .padding(start = 60.dp)
            )
            Text(
                text = "Date: ${id.dt_txt}",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 520.dp)
                    .padding(start = 60.dp)
            )
            Text(
                text = "Back",
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = color
                ),
                modifier = Modifier
                    .padding(top = 650.dp)
                    .padding(start = 60.dp)
                    .clickable {
                        finish()
                    }
            )
        }
    }
}