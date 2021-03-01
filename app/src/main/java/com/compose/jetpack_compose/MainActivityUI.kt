package com.compose.jetpack_compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun NewsStory(listOfTitles: List<String>) {
    val image = painterResource(id = R.drawable.header)

    CustomTheme {


        Column(modifier = Modifier.padding(16.dp)) {

            val ringColor = remember {
                randomColor()
            }
            //4.dp is the radius of the shape applied to the imageView
            //order of clip and border is top to bottom
            val imageModifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .border(2.dp, ringColor, RoundedCornerShape(16.dp))
                .padding(4.dp)
                .clip(shape = RoundedCornerShape(16.dp))

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
            titleList(titles = listOfTitles)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewsStory(listOf())
}

@Composable
fun titleList(titles: List<String>) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Cyan
    ) {


        LazyColumn {
            items(titles.size) { item ->
                var isSelected = remember { mutableStateOf(false) }
                val backgroundColor =
                    animateColorAsState(if (isSelected.value) Color.Red else Color.Transparent)

                Text(
                    titles[item],
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


fun randomColor(): Color = Color(
    red = Random.nextInt(0, 255),
    green = Random.nextInt(0, 255),
    blue = Random.nextInt(0, 255)
)

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                }
            )
        }
    ) {
            innerPadding ->
        Text("Nikhil", Modifier.padding(innerPadding))
    }


}

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    val primaryColor = Color(0xffae5d3c)
    val secondaryColor = Color(0xff000000)
    val colors = lightColors(primary = primaryColor, primaryVariant = primaryColor,
        secondary = secondaryColor, secondaryVariant = secondaryColor,
        surface = secondaryColor, onPrimary = primaryColor, onSecondary = secondaryColor)
    MaterialTheme(colors = colors, content = content)
}
