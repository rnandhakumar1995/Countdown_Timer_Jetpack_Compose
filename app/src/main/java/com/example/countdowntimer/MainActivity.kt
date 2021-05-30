package com.example.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.platform.LocalDensity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountdownTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GenerateTimerUI()
                }
            }
        }
    }
}

@Composable
fun GenerateTimerUI() {
    var endTime by remember { mutableStateOf(10 * 1000L) }
    val dashCount = endTime / 1000f
    val radius = 100f
    var endAngle by remember { mutableStateOf(360f) }
    val diameter = radius * 2
    val circumference =
        with(LocalDensity.current) { diameter.dp.toPx() * Math.PI * 2 }
    val dashPlusGapSize = circumference.toFloat().div(dashCount)
    object : CountDownTimer(endTime, 1000) {
        override fun onTick(remainingSecs: Long) {
            endAngle = ((remainingSecs * 360) / endTime).toFloat()
        }

        override fun onFinish() {
            endAngle = 0f
        }
    }.start()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .fillMaxHeight()
            .wrapContentSize(Alignment.Center)
    ) {
        TextField(value = dashCount.toString(), onValueChange = {
            endTime = (if (it == "") 0L else (it.toLongOrNull() ?: 0L)) * 1000L
        })
        Box(
            modifier = Modifier
                .size(diameter.dp)
                .drawBehind {
                    val style = Stroke(
                        width = 80f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(
                                dashPlusGapSize * 0.25f, dashPlusGapSize * 0.75f
                            ), 0f
                        )
                    )
                    drawArc(
                        Brush.linearGradient(listOf(Color.DarkGray, Color.LightGray)),
                        -90f,
                        endAngle,
                        false,
                        style = style
                    )
                },
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CountdownTimerTheme {
        GenerateTimerUI()
    }
}