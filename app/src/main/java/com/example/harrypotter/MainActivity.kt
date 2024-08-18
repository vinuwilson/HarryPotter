package com.example.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.harrypotter.presenter.navigation.Navigation
import com.example.harrypotter.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarryPotterApp()
        }
    }
}

@Composable
fun HarryPotterApp() {
    HarryPotterTheme {
        Navigation()
    }
}


@Preview(showBackground = true)
@Composable
fun HarryPotterAppPreview() {
    HarryPotterApp()
}