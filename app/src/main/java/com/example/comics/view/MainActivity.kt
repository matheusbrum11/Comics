package com.example.comics.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.comics.compose.ui.theme.ComicsTheme
import com.example.comics.ui.ComicsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicsTheme {
                ComicsScreen()
            }
        }
    }

}