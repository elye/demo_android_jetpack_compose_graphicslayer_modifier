package com.example.graphiclayermodifier

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.abs

enum class FLIPTYPE {
    HORIZONTAL, VERTICAL,
}

@Composable
fun DoubleSide(
    translationX: Float = 0f,
    translationY: Float = 0f,
    rotationX: Float = 0f,
    rotationY: Float = 0f,
    rotationZ: Float = 0f,
    cameraDistance: Float = 8f,
    flipType: FLIPTYPE,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    fun isHorizontallyFlip() = (abs(rotationX) % 360 > 90f && abs(rotationX) % 360 < 270f)
    fun isVerticallyFlip() = (abs(rotationY) % 360 > 90f && abs(rotationY) % 360 < 270f)

    // xor is used, because when both flipped, it is no longer flipped.
    fun isFlipped() = isVerticallyFlip() xor isHorizontallyFlip()

    if (isFlipped()) {
        val rotationXBack =
            if (flipType == FLIPTYPE.HORIZONTAL)
                rotationX - 180
            else
                rotationX

        val rotationYBack =
            if (flipType == FLIPTYPE.VERTICAL)
                rotationY - 180
            else
            // Need to negative value, suspecting a bug on rotation when a flipped
            // horizontally card is rotating left to right, it will reverse
            // hence need to negate it
                -rotationY
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationXBack,
                    rotationY = rotationYBack,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            back()
        }
    } else {
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationX,
                    rotationY = rotationY,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            front()
        }
    }
}
