package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashView(isError: Boolean, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource("compose-multiplatform.xml"),
            contentDescription = "Logo",
            modifier = Modifier.size(128.dp)
        )
        SlidingDialog(
            showDialog = isError,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            onDismiss = { onRetry() })
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SlidingDialog(showDialog: Boolean, modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    if (!showDialog) return
    var isOpening by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        isOpening = true
    }
    Box(
        modifier = modifier.dialogAnimation(isOpening = isOpening, bgColor = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(400.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.Center)
                .background(color = Color.Black)
        ) {
            Column(
                horizontalAlignment= Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,

                modifier = Modifier.matchParentSize()
            ) {

                Image(
                    painter = painterResource("compose-multiplatform-white.xml"),
                    contentDescription = "Logo",
                    modifier = Modifier.size(128.dp)
                )

                Text(
                    text = "Oops..!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )

                Text(
                    text = "Something went wrong try again",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White
                )

                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                        .clickable {
                            scope.launch {
                                isOpening = false
                                delay(1000)
                                onDismiss()
                            }
                        },
                    contentAlignment = Alignment.Center)
                {
                    Text(
                        text = "Retry",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
//                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}