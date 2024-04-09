package com.example.blackcoffer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.blackcoffer.ui.screens.ExploreScreen
import com.example.blackcoffer.ui.theme.BlackCofferTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlackCofferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    ExploreScreen(viewModel = ExploreViewModel())
                }
            }
        }
    }
}
