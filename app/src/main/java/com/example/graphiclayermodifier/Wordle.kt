package com.example.graphiclayermodifier

import android.view.animation.BounceInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
fun Wordle() {

    val flipAnimatable = remember { Animatable(0f) }


    val text = "GREAT"
    val padding = 24.dp
    var enabled by remember { mutableStateOf(true) }

    val transition = updateTransition(targetState = enabled, label = "")

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(5) {
                val rotationX by transition.animateFloat(
                    transitionSpec = {
                        tween(
                            delayMillis = it * 250,
                            durationMillis = it * 250 + 500
                        )
                    },
                    label = ""
                ) { if (it) 0f else 180f }

                DoubleSide(
                    rotationX = rotationX,
                    flipType = FLIPTYPE.HORIZONTAL,
                    cameraDistance = 100f,
                    front = {
                        MyLetterCard(
                            text[it].toString(),
                            Color.Black,
                            Color(58, 58, 60)
                        )
                    },
                    back = {
                        MyLetterCard(
                            text[it].toString(),
                            Color(95, 139, 85),
                            Color(95, 139, 85)
                        )
                    }
                )
            }

        }
        Button(onClick = {
            enabled = !enabled
        }) {
            Text("Click Me")
        }
    }
}

@Composable
fun MyLetterCard(text: String, color: Color, border: Color) {
    val roundedDegree = RoundedCornerShape(2.dp)
    Box(
        Modifier
            .clip(roundedDegree)
            .background(color)
            .size(70.dp, 70.dp)
            .border(2.dp, border, shape = roundedDegree),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Color.White
        )
    }
}
