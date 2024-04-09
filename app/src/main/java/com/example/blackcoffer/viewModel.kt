package com.example.blackcoffer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blackcoffer.data.ExploreState
import com.example.blackcoffer.data.FabOption
import com.example.blackcoffer.data.Screen

class ExploreViewModel : ViewModel() {

    private val _exploreState = MutableLiveData<ExploreState>()
    val exploreState: LiveData<ExploreState> = _exploreState

    init {
        // Initialize ViewModel state
        val initialExploreState = ExploreState(
            greeting = "Hello BlackCoffer!",
            addressLine1 = "123 Main St",
            addressLine2 = "City, Country",
            sections = listOf("Personal", "Services", "Businesses"),
            currentSectionIndex = 0,
            people = emptyList(), // Initialize with empty list
            bottomBarScreens = listOf(
                Screen("Explore", Icons.Default.Search),
                Screen("Connections", Icons.Default.Face),
                Screen("Chat", Icons.Default.MailOutline),
                Screen("Contacts", Icons.Default.AccountBox),
                Screen("Groups", Icons.Default.Person)
            ),
            fabOptions = listOf(
                FabOption("Dating", Icons.Default.Favorite),
                FabOption("Matrimony", Icons.Default.Favorite),
                FabOption("Buy-Sell-Rent", Icons.Default.ShoppingCart),
                FabOption("Business Cards", Icons.Default.Star),
                FabOption("Netclan Groups", Icons.Default.Person),
                FabOption("Jobs", Icons.Default.MoreVert),
                FabOption("Notes", Icons.Default.Create)
            ),
            currentScreenIndex = 0,
            isFabExtended = false // Initialize as not extended
        )
        _exploreState.value = initialExploreState
    }

    // Other ViewModel methods/functions would be defined here
}