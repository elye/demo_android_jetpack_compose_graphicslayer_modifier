package com.example.graphiclayermodifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DoubleSideCard() {

    val padding = 24.dp

    var rotationX by remember { mutableStateOf(0f) }
    var rotationY by remember { mutableStateOf(0f) }
    var rotationZ by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Back Horizontally Flipped")
        Spacer(modifier = Modifier.height(16.dp))
        DoubleSide(
            rotationX = rotationX,
            rotationY = rotationY,
            rotationZ = rotationZ,
            flipType = FLIPTYPE.HORIZONTAL,
            front = { MyCard("Front", Color.Yellow) },
            back = { MyCard("Back", Color.Gray) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Back Vertically Flipped")
        Spacer(modifier = Modifier.height(16.dp))
        DoubleSide(
            rotationX = rotationX,
            rotationY = rotationY,
            rotationZ = rotationZ,
            flipType = FLIPTYPE.VERTICAL,
            front = { MyCard("Front", Color.Yellow) },
            back = { MyCard("Back", Color.Gray) }
        )

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

@Composable
fun MyCard(text: String, color: Color) {
    val roundedDegree = RoundedCornerShape(8.dp)
    Box(
        Modifier
            .clip(roundedDegree)
            .background(color)
            .size(100.dp, 100.dp)
            .border(5.dp, Color.Red, shape = roundedDegree),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}
