package com.itzcorpio.gifapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.itzcorpio.gifapplication.presentation.gif_list.screen.GifListScreen
import com.itzcorpio.gifapplication.ui.theme.GifApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GifApplicationTheme {
                GifListScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GifListScreenPreview() {
    GifApplicationTheme {
        GifListScreen()
    }
}