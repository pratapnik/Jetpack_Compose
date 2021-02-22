package com.compose.jetpack_compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory(getListOfTitles())
        }
    }

    fun getListOfTitles(): List<String> {
        var i = 0
        val listOfTitles = mutableListOf<String>()
        while (i<50) {
            listOfTitles.add("Title -> ".plus(i+1))
            i++
        }
        return listOfTitles
    }
}

@Composable
fun NewsStory(listOfTitles: List<String>) {
    val image = imageResource(id = R.drawable.header)

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            val ringColor = remember {
                randomColor()
            }
            //4.dp is the radius of the shape applied to the imageView
            //order of clip and border is top to bottom
            val imageModifier = Modifier
                    .preferredHeight(180.dp)
                    .fillMaxWidth()
                    .border(2.dp, ringColor, RoundedCornerShape(16.dp))
                    .padding(4.dp)
                    .clip(shape = RoundedCornerShape(16.dp))

            Image(image,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop)
            Spacer(Modifier.preferredHeight(16.dp))

            Text("A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the sights I saw",
                    style = typography.h6,
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
        LazyColumnFor(items = titles) { item ->
            Text(item)
        }
    }
}


fun randomColor(): Color = Color (
        red = Random.nextInt(0, 255),
        green = Random.nextInt(0, 255),
        blue = Random.nextInt(0, 255)
        )



