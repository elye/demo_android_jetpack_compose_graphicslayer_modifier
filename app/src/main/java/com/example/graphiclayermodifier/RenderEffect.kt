package com.example.graphiclayermodifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RenderEffect() {

    var radius by remember { mutableStateOf(1f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.colorful_rose),
            contentDescription = "Poker Front",
            modifier = Modifier.graphicsLayer(
                renderEffect = BlurEffect(radius, radius),
                clip = true
            )
        )

        Slider(
            value = radius,
            { radius = it },
            valueRange = 1f..100f
        )
    }
}
