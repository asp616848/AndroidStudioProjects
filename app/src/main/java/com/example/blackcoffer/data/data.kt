package com.example.blackcoffer.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel

// FabOption.kt

data class FabOption(
    val label: String,
    val icon: ImageVector
)

// Screen.kt

data class Screen(
    val label: String,
    val icon: ImageVector
)

data class ExploreState(
    val greeting: String,
    val addressLine1: String,
    val addressLine2: String,
    val sections: List<String>,
    val currentScreenIndex: Int,
    val currentSectionIndex: Int,
    val people: List<People>,
    val bottomBarScreens: List<Screen>,
    val fabOptions: List<FabOption>,
    val isFabExtended: Boolean
) {
}

data class People(
    val name: String,
    val age: Int,
    // Other properties
)