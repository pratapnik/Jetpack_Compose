package com.compose.jetpack_compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.compose.jetpack_compose.ui.theme.ui.theme.Jetpack_ComposeTheme
import kotlin.concurrent.timer

class ConstraintActivity : AppCompatActivity() {

    private val time = mutableStateOf(0)
    private val timerRoutine = timer(period = 1000) { time.value++ }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    ConstraintLayoutContent()
                    Text(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(Color.Red),
                        text = time.value.toString(),
                    color = Color.White,
                    textAlign = TextAlign.Center)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerRoutine.cancel()
    }
}

@Preview
@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val button = createRef()
        val text = createRef()

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text("Button")
        }
        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            width = Dimension.fillToConstraints
        })
    }
}