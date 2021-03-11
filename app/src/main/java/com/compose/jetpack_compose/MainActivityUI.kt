package com.compose.jetpack_compose

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NewsStory(listOfTitles: List<String>, onButtonClick: () -> Unit) {
    val image = painterResource(id = R.drawable.header)

    MaterialTheme {

        Column(modifier = Modifier.padding(16.dp)) {

            val ringColor = remember {
                randomColor()
            }
//            val ringColor = randomColor()
            //4.dp is the radius of the shape applied to the imageView
            //order of clip and border is top to bottom
            val imageModifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(130.dp)
                .border(2.dp, ringColor, RoundedCornerShape(16.dp))
                .clip(shape = RoundedCornerShape(16.dp))
            Row {
                AlertDialogComponent()
                Button(onClick = {
                    onButtonClick()
                }) {
                    Text(text = "Go to Constraint Activity")
                }
            }

            Image(
                image,
                modifier = imageModifier,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.requiredHeight(16.dp))

            Text(
                "A day wandering through the sandhills " +
                        "in Shark Fin Cove, and a few of the sights I saw",
                style = MaterialTheme.typography.h6,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
                fontSize = dimensionResource(id = R.dimen.heading_text_size).value.sp
            )

            Text("Davenport, California")
            Text("December 2018")

            val counterState = remember { mutableStateOf(0) }
            Counter(
                count = counterState.value,
                updateCount = { newCount ->
                    counterState.value = newCount + 3
                }
            )
            AnimatedVisibility()
            TitleList(titles = listOfTitles)
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun DefaultPreview() {
    NewsStory(listOf(), onButtonClick = {})
//    DisplayName()
}

@Composable
fun DisplayName() {
    Text("Nikhil")
}

@ExperimentalFoundationApi
@Composable
fun TitleList(titles: List<String>) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Cyan
    ) {


        LazyColumn {
            val grouped = titles.sorted().groupBy { it[0] }
            grouped.forEach {
                stickyHeader {
                    Text(
                        text = it.key.toString(), Modifier
                            .fillMaxWidth()
                            .background(Color.Black), color = Color.White
                    )
                }
                items(it.value.size) { item ->
                    var isSelected = remember { mutableStateOf(false) }
                    val backgroundColor =
                        animateColorAsState(if (isSelected.value) Color.Red else Color.Transparent)

                    Text(
                        text = it.value[item],
                        modifier = Modifier
                            .padding(10.dp)
                            .background(color = backgroundColor.value)
                            .clickable(onClick = {
                                isSelected.value = !isSelected.value
                            })
                    )
                    Divider(color = Color.Black)
                }
            }
        }
    }
}




fun randomColor(): Color = Color(
    red = Random.nextInt(0, 255),
    green = Random.nextInt(0, 255),
    blue = Random.nextInt(0, 255)
)

@ExperimentalAnimationApi
@Composable
fun AnimatedVisibility() {
    val visible = remember { mutableStateOf(true) }

    Row (Modifier.padding(16.dp)){
        Button(onClick = { visible.value = !visible.value }) {
            Text(text = if (visible.value) "HIDE" else "SHOW")
        }
        Spacer(modifier = Modifier.width(16.dp))

        AnimatedVisibility(visible = visible.value) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Black)
            )
        }
    }
}

@Composable
fun AlertDialogComponent() {
    val showPopup = remember { mutableStateOf(false) }
    val onButtonClicked = { showPopup.value = true }
    val onPopupDismissed = { showPopup.value = false }
    Log.d("nikhil", "AlertDialogComponent: " + showPopup.value)
    if (!showPopup.value) {
        Button(
            onClick = { onButtonClicked() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Text(text = "SHOW POPUP", color = Color.White)
        }
    } else {
        AlertDialog(
            onDismissRequest = {
                onPopupDismissed()
            },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text("Here is a text ")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onPopupDismissed()
                    }) {
                    Text("OK")
                }
            },
        )
    }
}




