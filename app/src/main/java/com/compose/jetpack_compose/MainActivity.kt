package com.compose.jetpack_compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory(getListOfTitles(),  onButtonClick = {
                startActivity(Intent(this, ConstraintActivity::class.java))
            })
        }
    }

    private fun getListOfTitles(): List<String> {
        return listOf("Ram", "Shyam", "Raja", "Rani", "Nikhil", "John", "Oliver", "Abhi", "Adam")
    }
}




