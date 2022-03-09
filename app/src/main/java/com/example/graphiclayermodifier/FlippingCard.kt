package com.example.graphiclayermodifier

import android.animation.TimeInterpolator
import android.view.animation.AnticipateInterpolator
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun TimeInterpolator.toEasing() = Easing { x -> getInterpolation(x) }

@Composable
fun FlippingCard() {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val padding = 100.dp
    val paddingSide = 20.dp
    val boxSize = 100.dp

    with(LocalDensity.current) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding, paddingSide, padding, paddingSide)
                .onSizeChanged {
                    size = it
                },
            verticalArrangement = Arrangement.Center
        ) {

            var enabled by remember {
                mutableStateOf(true)
            }
            val valueFloat: Float by animateFloatAsState(
                if (enabled) 0f else 1f,
                animationSpec = tween(
                    durationMillis = 5000,
                    easing = AnticipateInterpolator().toEasing()
                )
            )
            val roundedDegree = RoundedCornerShape(8.dp)

            DoubleSide(
                translationX = valueFloat * (size.width - boxSize.toPx()),
                rotationY = valueFloat * 720,
                rotationZ = valueFloat * 180,
                cameraDistance = 16f,
                flipType = FLIPTYPE.HORIZONTAL,
                front = {
                    Image(
                        painterResource(R.drawable.card_front),
                        contentDescription = "Poker Front",
                        modifier = Modifier
                            .size(boxSize, boxSize * 3 / 2)
                            .border(2.dp, Color.Black, shape = roundedDegree)
                    )
                }, back = {
                    Image(
                        painterResource(R.drawable.card_back),
                        contentDescription = "Poker Front",
                        modifier = Modifier
                            .size(boxSize, boxSize * 3 / 2)
                            .border(2.dp, Color.Black, shape = roundedDegree)
                    )
                })
            
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { enabled = !enabled }) {
                    Text("Click Me")
                }
            }
        }
    }
}