package com.example.blackcoffer.ui.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.blackcoffer.data.Screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomActionBar(
    screens: List<Screen>,
    currentScreenIndex: Int,
    onScreenChange: (Int) -> Unit
) {
    BottomNavigation {
        screens.forEachIndexed { index, screen ->
            BottomNavigationItem(
                icon = screen.icon,
                label = screen.label,
                selected = currentScreenIndex == index,
                onClick = { onScreenChange(index) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomActionBar() {
    val screens = listOf(
        Screen("Explore", Icons.Default.Search),
        Screen("Connections", Icons.Default.AccountBox),
        Screen("Chat", Icons.Default.MailOutline),
        Screen("Contacts", Icons.Default.AccountBox),
        Screen("Groups", Icons.Default.Face)
    )
    BottomActionBar(
        screens = screens,
        currentScreenIndex = 0,
        onScreenChange = {}
    )
}

@Composable
fun BottomNavigation(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
@Composable
fun BottomNavigationItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = label,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    )
    if (selected) {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp)
        )
    }
}
