package com.example.refineandexplore

import RefineScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.refineandexplore.ui.screens.ExploreScreen
import com.example.refineandexplore.ui.theme.RefineAndExploreTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            RefineAndExploreTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "explore_screen") {
                    composable("explore_screen") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            ExploreScreen(viewModel = viewModel(), modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                    }
                    composable("refine_screen") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            RefineScreen(innerPadding, onBack = { navController.popBackStack() }, onSaveAndExplore = { navController.navigate("explore_screen") }, availabilityOptions = listOf("Available", "Busy", "Away"), statusOptions = listOf("Online", "Offline"))
                        }
                    }
                }
            }
        }
    }
}
