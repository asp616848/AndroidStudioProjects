package com.example.blackcoffer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

// TopActionBar.kt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopActionBar(
    greeting: String,
    addressLine1: String,
    addressLine2: String,
    onRefineClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text(text = greeting)
                Text(text = addressLine1)
                Text(text = addressLine2)
            }
        },
        navigationIcon = {
            // Non-functional navigation icon
        },
        actions = {
            IconButton(onClick = onRefineClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Refine"
                )
                Text(text = "Refine")
            }
        }
    )
}
// NavigationBar.kt

@Composable
fun NavigationBar(
    sections: List<String>,
    currentSectionIndex: Int,
    onSectionChange: (Int) -> Unit
) {
    TabRow(selectedTabIndex = currentSectionIndex) {
        sections.forEachIndexed { index, section ->
            Tab(
                selected = currentSectionIndex == index,
                onClick = { onSectionChange(index) }
            ) {
                Text(text = section)
            }
        }
    }
}

