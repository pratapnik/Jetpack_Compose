package com.compose.jetpack_compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }
}

@Composable
fun NewsStory() {
    val image = imageResource(id = R.drawable.header)

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            //4.dp is the radius of the shape applied to the imageView
            val imageModifier = Modifier
                    .preferredHeight(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))

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
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewsStory()
}

