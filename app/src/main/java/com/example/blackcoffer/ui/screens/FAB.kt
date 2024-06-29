package com.example.blackcoffer.ui.screens

// FloatingActionButton.kt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blackcoffer.data.FabOption

@Composable
fun FloatingActionButton(
    extended: Boolean,
    options: List<FabOption>,
    onOptionSelected: (FabOption) -> Unit
) {
    ExtendedFloatingActionButton(text = { /*TODO*/ }, icon = { /*TODO*/ }, onClick = { /*TODO*/ })
}


@Composable
fun FloatingActionButtonOption(
    option: FabOption,
    onOptionSelected: (FabOption) -> Unit
) {
    ExtendedFloatingActionButton(
        text = { /* Text for the option if needed */ },
        onClick = { onOptionSelected(option) },
        icon = { Icon(option.icon, contentDescription = option.label) },
        modifier = Modifier.padding(16.dp) // Adjust padding as needed
    )
}

@Preview
@Composable
fun PreviewFloatingActionButton() {
    val options = listOf(
        FabOption("Option 1", Icons.Default.Favorite),
        FabOption("Option 2", Icons.Default.Add),
        // Add more options as needed
    )
    FloatingActionButton(
        extended = true,
        options = options,
        onOptionSelected = { /* Handle option selection */ }
    )
}
