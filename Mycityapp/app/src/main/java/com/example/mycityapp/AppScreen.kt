package com.example.mycityapp

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycityapp.Screens.CatergoryScreen
import com.example.mycityapp.Screens.RecomendationsScreen


enum class AppScreen(val title: String) {
    CatergoryScreen("Categories"),
    RecommendationsScreen("Recomendations"),
    DetailsScreen("Details")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(appScreen: AppScreen,
           canNavigateBack :Boolean,
           navigateUp: () -> Unit,
           modifier : Modifier= Modifier) {
    TopAppBar(
        title = {
            Text(text = appScreen.title)
        },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon (
                        imageVector =  Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        }
    )
}

@Composable
fun AppScreenWhole(
    navController: NavHostController = rememberNavController(),
){
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.CatergoryScreen.name
    )

    Scaffold(
        topBar = { AppBar(appScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { /*TODO*/ }) }
    ) { innerPadding ->

        NavHost(navController = navController,
            startDestination = AppScreen.CatergoryScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding))
        {

            composable(AppScreen.CatergoryScreen.name){
                CatergoryScreen(navController = navController)
            }
            composable("${AppScreen.RecommendationsScreen.name}/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType }))
            {backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index") ?: 0
                // Use the index in your RecomendationsScreen composable
                RecomendationsScreen(navController, index)
            }
        }
    }
}