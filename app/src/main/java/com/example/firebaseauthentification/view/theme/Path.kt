package com.example.firebaseauthentification.view.theme

import android.graphics.Path
import androidx.compose.ui.geometry.Offset

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadratic(from.x, from.y, Math.abs(from.x + to.x) / 2f, Math.abs(from.y + to.y) / 2f)
}

private fun Path.quadratic(x: Float, y: Float, fl: Float, fl1: Float) {


}