package com.example.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countdowntimer.ui.theme.CountdownTimerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountdownTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

val TAG = "MainActivity"

@Composable
fun Greeting() {
    var numberOfSplits by remember { mutableStateOf(270f) }
    val endTime = 10000L
    val totalNumberOfSplits = endTime / 1000L
    object : CountDownTimer(endTime, 1000) {
        override fun onTick(remainingSecs: Long) {
            numberOfSplits = (remainingSecs / 1000L).toFloat()
        }

        override fun onFinish() {
            numberOfSplits = 0f
        }
    }.start()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .fillMaxHeight()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .drawBehind {
                    for (i in 0 until totalNumberOfSplits) {
                        val eachAngle = 360f / totalNumberOfSplits
                        val startAngle = -90f + (i * eachAngle)
                        val endAngle = eachAngle - 3
                        drawArc(
                            if (i > totalNumberOfSplits - numberOfSplits) Brush.linearGradient(
                                listOf(
                                    Color.White,
                                    Color.White
                                )
                            ) else Brush.linearGradient(
                                listOf(Color.Red, Color.Green)
                            ),
                            (startAngle),
                            (endAngle),
                            false,
                            style = Stroke(width = 70f)
                        )
                    }
                },

            )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CountdownTimerTheme {
        Greeting()
    }
}