package com.example.refineandexplore.ui.screens

import android.app.Activity
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material.icons.twotone.Build
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.refineandexplore.data.UiState
import com.example.refineandexplore.viewModel
import com.example.refineandexplore.data.UserLocation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(viewModel: viewModel, modifier: Modifier) {
    val uiState by viewModel.uiState.observeAsState(initial = UiState())
    var presses by remember { mutableStateOf(0) }
    val context = LocalContext.current


    Scaffold (
        topBar = {
            TopAppBar(
                title = { title(uiState) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }){
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Navigation Menu",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                actions = {
                    Column(
                        modifier = Modifier.padding(8.dp)

                    ) {
                        IconButton(onClick = { /*TODO*/ }){
                            Icon(
                                imageVector = Icons.TwoTone.Edit,
                                contentDescription = "Refine",
                                modifier = Modifier
                                    .size(40.dp)
                                    .offset(6.dp),
                            )
                        }
                        Text(
                            text = "Refine",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.offset(y = -6.dp)
                        )
                    }
                },
            )
        },


        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.List, contentDescription = "List")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Sharp.Settings, contentDescription = "Settings")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}

@Composable
fun title(uiState: UiState) {
    Row {
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                text = uiState.userName, // Display the username from uiState
                style = MaterialTheme.typography.titleMedium,
            )
            Row {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    Modifier
                        .size(17.dp)
                        .padding(start = 4.dp)
                )
                Text(
                    text = uiState.address, // Display the location
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(viewModel = viewModel(), modifier = Modifier)
}
