package com.example.blackcoffer.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blackcoffer.ExploreViewModel


// ExploreScreen.kt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel // Assuming you have an ExploreViewModel defined
) {
    val state by viewModel.exploreState.observeAsState()

    // Observe other necessary states from the ViewModel

    Scaffold(
        topBar = {
            TopActionBar(
                greeting = state?.greeting ?: "",
                addressLine1 = state?.addressLine1 ?: "",
                addressLine2 = state?.addressLine2 ?: "",
                onRefineClick = { /* Handle refine click */ }
            )
        },
        bottomBar = {
            BottomActionBar(
                screens = state?.bottomBarScreens ?: emptyList(),
                currentScreenIndex = state?.currentScreenIndex ?: 0,
                onScreenChange = { /* Handle screen change */ }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                extended = state?.isFabExtended ?: false,
                options = state?.fabOptions ?: emptyList(),
                onOptionSelected = { /* Handle FAB option selected */ }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                NavigationBar(
                    sections = state?.sections ?: emptyList(),
                    currentSectionIndex = state?.currentSectionIndex ?: 0,
                    onSectionChange = { /* Handle section change */ }
                )
                Spacer(modifier = Modifier.height(40.dp))
                SearchTab(
                    onFilterClick = { /* Handle filter click */ }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PeopleList(
                    people = state?.people ?: emptyList()
                )
            }
        }
    )
}

@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(viewModel = ExploreViewModel())
}


