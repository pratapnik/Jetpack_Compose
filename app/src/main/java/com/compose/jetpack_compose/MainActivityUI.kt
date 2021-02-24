package com.compose.jetpack_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

    MaterialTheme {
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

            Image(image,
                modifier = imageModifier,
                contentDescription = "",
                contentScale = ContentScale.Crop)
            Spacer(Modifier.requiredHeight(16.dp))

            Text("A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the sights I saw",
                style = MaterialTheme.typography.h6,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
                fontSize = dimensionResource(id = R.dimen.heading_text_size).value.sp)
            Text("Davenport, California")
            Text("December 2018")
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
    Surface(Modifier.fillMaxWidth()) {
        LazyColumn{
            items(titles.size) {
                    item -> Text(titles[item])
            }
        }
    }
}


fun randomColor(): Color = Color (
    red = Random.nextInt(0, 255),
    green = Random.nextInt(0, 255),
    blue = Random.nextInt(0, 255)
)
