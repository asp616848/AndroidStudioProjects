package com.example.blackcoffer.ui.screens

import android.app.Person
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.blackcoffer.data.People

// SearchTab.kt

@Composable
fun SearchTab(
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Search bar implementation
        IconButton(onClick = onFilterClick) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Filter"
            )
        }
    }
}
// PeopleList.kt

@Composable
fun PeopleList(people: List<People>) {
    LazyColumn {
        items(people) { person ->
            // Composable for displaying person details
        }
    }
}

@Preview
@Composable
fun PreviewPeopleList() {
    val people = listOf(
        People("John Doe", 25),
        People("Jane Doe", 30)
    )
    PeopleList(people)
}