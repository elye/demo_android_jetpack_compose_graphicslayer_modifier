package com.example.graphiclayermodifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GraphicsLayerSetting() {

    var scaleX by remember { mutableStateOf(1f) }
    var scaleY by remember { mutableStateOf(1f) }
    var alpha by remember { mutableStateOf(1f) }
    var originX by remember { mutableStateOf(0.5f) }
    var originY by remember { mutableStateOf(0.5f) }
    var translationX by remember { mutableStateOf(0f) }
    var translationY by remember { mutableStateOf(0f) }
    var cameraDistance by remember { mutableStateOf(8f) }
    var shadowElevation by remember { mutableStateOf(0f) }
    var rotationX by remember { mutableStateOf(0f) }
    var rotationY by remember { mutableStateOf(0f) }
    var rotationZ by remember { mutableStateOf(0f) }
    var blur by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val roundedDegree = RoundedCornerShape(8.dp)
        CircleShape
        Box(
            Modifier
                .graphicsLayer(
                    alpha = alpha,
                    translationX = translationX,
                    translationY = translationY,
                    shadowElevation = shadowElevation,
                    scaleX = scaleX,
                    scaleY = scaleY,
                    rotationX = rotationX,
                    rotationY = rotationY,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance,
                    transformOrigin = TransformOrigin(originX, originY),
                    shape = roundedDegree,
                    clip = true,
                    renderEffect = BlurEffect(blur, blur)
                )
                .background(Color(97, 140, 85))
                .size(100.dp, 100.dp)
                .border(5.dp, Color.Red, shape = roundedDegree)
                ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                "NE",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
        }

        toggle("Alpha", alpha, 0f..1f) { alpha = it }
        toggle("TranslationX", translationX, -100f..100f) { translationX = it }
        toggle("TranslationY", translationY, -100f..100f) { translationY = it }
        toggle("Shadow", shadowElevation, 0f..500f) { shadowElevation = it }
        toggle("ScaleX", scaleX, 0.5f..2f) { scaleX = it }
        toggle("ScaleY", scaleY, 0.5f..2f) { scaleY = it }
        toggle("RotateX", rotationX, -360f..360f) { rotationX = it }
        toggle("RotateY", rotationY, -360f..360f) { rotationY = it }
        toggle("RotateZ", rotationZ, -360f..360f) { rotationZ = it }
        toggle("OriginX", originX, 0f..1f) { originX = it }
        toggle("OriginY", originY, 0f..1f) { originY = it }
        toggle("CameraDist", cameraDistance, 3f..50f) { cameraDistance = it }
        toggle("Blur", blur, 1f..100f) { blur = it }
    }
}

@Composable
private fun toggle(text: String,
                   value: Float,
                   range: ClosedFloatingPointRange<Float>,
                   onValueChange: (Float) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(30.dp)
    ) {
        Text(text, modifier = Modifier.width(100.dp))
        Slider(
            value = value,
            onValueChange,
            valueRange = range
        )
    }
}