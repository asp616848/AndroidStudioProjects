package com.example.refineandexplore.ui.screens

import SwipeScreen
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.refineandexplore.data.UiState
import com.example.refineandexplore.ui.ExpandedButton
import com.example.refineandexplore.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(viewModel: viewModel, modifier: Modifier) {
    val uiState by viewModel.uiState.observeAsState(initial = UiState())
    val viewModel = viewModel()
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
            var expanded by rememberSaveable {
                mutableStateOf(viewModel.uiState.value?.expanded)
            }

            val transition = updateTransition(targetState = expanded)
            val rotation by transition.animateFloat(label = "rotation") { state ->
                if (state == true) 179f else 0f
            }

            if (expanded == true) {
                Column {
                    ExpandedButton(viewModel = viewModel)
                    Spacer(modifier = Modifier.size(16.dp))
                    FloatingActionButton(
                        onClick = { expanded = !expanded!! },
                        shape = CircleShape,
                        content = {
                            Icon(Icons.Rounded.Clear, contentDescription = "Clear")
                        },
                        modifier = Modifier
                            .offset(195.dp)
                            .rotate(rotation)
                            .animateContentSize(), // animate size changes
                        elevation = FloatingActionButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    )
                }
            } else if (expanded == false) {
                FloatingActionButton(
                    onClick = {expanded = !expanded!!},
                    shape = CircleShape,
                    content = {
                        Icon(Icons.Rounded.Add, contentDescription = "Add")
                    },
                    modifier = Modifier
                        .animateContentSize()
                        .rotate(rotation), // animate size changes
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )
                )
            }
        },

        bottomBar = {
            BottomAppBar {
                Row{
                    Spacer(modifier = Modifier.size(2.dp))
                    BottomElement(text = "Explore", icon = Icons.Outlined.Search)
                    Spacer(modifier = Modifier.size(18.dp))
                    BottomElement(text = "Connections", icon = Icons.Outlined.Person)
                    Spacer(modifier = Modifier.size(18.dp))
                    BottomElement(text = "Chat", icon = Icons.Outlined.Email)
                    Spacer(modifier = Modifier.size(18.dp))
                    BottomElement(text = "Contacts", icon = Icons.Outlined.Call)
                    Spacer(modifier = Modifier.size(18.dp))
                    BottomElement(text = "Groups", icon = Icons.TwoTone.Notifications)
                }
            }
        }
    ) { innerPadding ->
            SwipeScreen(innerPadding)
    }
}


@Composable
fun title(uiState: UiState?) {
    if (uiState != null) {
        Row {
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = uiState.userName, // Display the username from uiState
                    style = MaterialTheme.typography.titleLarge,
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
    } else {
        // Handle the null case if needed, e.g., show placeholder text
        Text(text = "Loading...", modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(viewModel = viewModel(), modifier = Modifier)
}