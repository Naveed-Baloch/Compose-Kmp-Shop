package ui


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.dialogAnimation(isOpening: Boolean, bgColor: Color) = composed {
    val configuration = 500f
    val rotationAngle = remember { mutableStateOf(30f) }
    val dialogOffset = remember { mutableStateOf(Offset(-(configuration + 200), -200f)) }
    val backGroundColorAlpha = remember { mutableStateOf(0f) }
    val animatedBgAlpha by animateFloatAsState(targetValue = backGroundColorAlpha.value, animationSpec = tween(durationMillis = 1000), label = "Animated Offset")
    val animatedOffset by animateOffsetAsState(targetValue = dialogOffset.value, animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing), label = "Animated Offset")
    val rotationAnimation by animateFloatAsState(targetValue = rotationAngle.value, animationSpec = tween(1000), label = "Rotation Animation")
    if (isOpening) {
        backGroundColorAlpha.value = 0.2f
        dialogOffset.value = Offset(x = 0f, y = configuration / 50)
        rotationAngle.value = 0f
        this
            .background(color = Color.Black.copy(alpha = animatedBgAlpha))
            .offset(x = animatedOffset.x.dp, y = animatedOffset.y.dp)
            .rotate(rotationAnimation)
    } else {
        backGroundColorAlpha.value = 0f
        dialogOffset.value = Offset(x = configuration + 200, y = -200f)
        rotationAngle.value = -30f
        this
            .background(color = bgColor.copy(alpha = animatedBgAlpha))
            .offset(x = animatedOffset.x.dp, y = animatedOffset.y.dp)
            .rotate(rotationAnimation)
    }
}