package com.example.refineandexplore.ui.screens

import SwipeScreen
import android.widget.LinearLayout
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.refineandexplore.data.UiState
import com.example.refineandexplore.ui.ExpandedButton
import com.example.refineandexplore.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(viewModel: viewModel, modifier: Modifier, navController: NavHostController) {
    val uiState by viewModel.uiState.observeAsState(initial = UiState())
    val viewModel = viewModel()
    val context = LocalContext.current

    // Variable to track the expanded state of the FAB
    var expanded by rememberSaveable { mutableStateOf(viewModel.uiState.value?.expanded) }

    // Transition for animating the rotation of the FAB
    val transition = updateTransition(targetState = expanded)
    val rotation by transition.animateFloat(label = "rotation") { state ->
        if (state == true) 179f else 0f
    }
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
                        IconButton(onClick = { navController.navigate("refine_screen") }){
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

            if (expanded == true || expanded == null) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (box, column) = createRefs()

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(box) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.4f))
                            .clickable { expanded = !expanded!! }
                    )

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .constrainAs(column) {
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    ) {
                        ExpandedButton(viewModel = viewModel)
                        Spacer(modifier = Modifier.height(30.dp))
                        Row{
                            Text(text = "                                            ")
                            FloatingActionButton(
                                onClick = { expanded = !expanded!! },
                                shape = CircleShape,
                                content = {
                                    Icon(Icons.Rounded.Clear, contentDescription = "Clear")
                                },
                                modifier = Modifier
                                    .rotate(rotation)
                                    .animateContentSize(), // animate size changes
                                elevation = FloatingActionButtonDefaults.elevation(
                                    defaultElevation = 0.dp
                                )
                            )
                        }
                    }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    BottomElement(text = "Explore", icon = Icons.Outlined.Search)
                    BottomElement(text = "Connections", icon = Icons.Outlined.Person)
                    BottomElement(text = "Chat", icon = Icons.Outlined.Email)
                    BottomElement(text = "Contacts", icon = Icons.Outlined.Call)
                    BottomElement(text = "Groups", icon = Icons.TwoTone.Notifications)
                }
            }
        }

    ) { innerPadding ->
            SwipeScreen(viewModel, innerPadding)
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
    ExploreScreen(viewModel(), Modifier, navController = NavHostController(LocalContext.current))
}