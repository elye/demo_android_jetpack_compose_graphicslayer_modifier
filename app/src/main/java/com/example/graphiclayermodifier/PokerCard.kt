package com.example.graphiclayermodifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun PokerCard() {

    val padding = 24.dp

    var rotationX by remember { mutableStateOf(0f) }
    var rotationY by remember { mutableStateOf(0f) }
    var rotationZ by remember { mutableStateOf(0f) }
    val roundedDegree = RoundedCornerShape(8.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))
        DoubleSide(
            rotationX = rotationX,
            rotationY = rotationY,
            rotationZ = rotationZ,
            cameraDistance = 16f,
            flipType = FLIPTYPE.HORIZONTAL,
            front = {
                Image(
                    painterResource(R.drawable.card_front),
                    contentDescription = "Poker Front",
                    modifier = Modifier.border(2.dp, Color.Black, shape = roundedDegree)
                )
            }, back = {

                Image(
                    painterResource(R.drawable.card_back),
                    contentDescription = "Poker Front",
                    modifier = Modifier.border(2.dp, Color.Black, shape = roundedDegree)
                )
            })
        Spacer(modifier = Modifier.height(32.dp))

        Text("Horizontal Flip (X Axis)")
        Slider(
            value = rotationX,
            { rotationX = it },
            valueRange = -360f..360f
        )
        Text("Vertical Flip (Y Axis)")
        Slider(
            value = rotationY,
            { rotationY = it },
            valueRange = -360f..360f
        )
        Text("Rotation (Z Axis)")
        Slider(
            value = rotationZ,
            { rotationZ = it },
            valueRange = -360f..360f
        )
    }
}
