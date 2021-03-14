package com.example.countdowntimer.ui.theme

import android.graphics.DashPathEffect
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TimerProgressBar : Shape {
    val TAG = "TimerProgressBar"
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
                val rect = Rect(Offset.Zero, size)
                val circle = Path().apply {
                    addArc(rect, -90f, 180f)
                    close()
                }
                return Outline.Generic(circle)
                }
}