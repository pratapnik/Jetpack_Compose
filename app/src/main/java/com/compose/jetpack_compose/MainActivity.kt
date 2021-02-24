package com.compose.jetpack_compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory(getListOfTitles())
        }
    }

    private fun getListOfTitles(): List<String> {
        var i = 0
        val listOfTitles = mutableListOf<String>()
        while (i<50) {
            listOfTitles.add("Title -> ".plus(i+1))
            i++
        }
        return listOfTitles
    }
}




