package com.shubhamkumarwinner.composepaging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import coil.annotation.ExperimentalCoilApi
import com.shubhamkumarwinner.composepaging.ui.screen.CharacterScreen
import com.shubhamkumarwinner.composepaging.ui.theme.ComposePagingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePagingTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CharacterScreen()
                }
            }
        }
    }
}
