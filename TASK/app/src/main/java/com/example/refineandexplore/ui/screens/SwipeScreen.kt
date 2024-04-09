import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SwipeScreen(innerPadding: PaddingValues = PaddingValues()) {
    var currentTab by remember { mutableStateOf(0) }

    val tabs = listOf("Personal", "Service", "Businesses")

    Column(modifier = Modifier.padding(innerPadding)) {
        // Top bar showing current screen
        TabRow(selectedTabIndex = currentTab) {
            tabs.forEachIndexed { index, text ->
                Tab(
                    text = { Text(text) },
                    selected = currentTab == index,
                    onClick = { currentTab = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search box and filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search box
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search") }
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Filter button
            IconButton(onClick = { /* Handle filter click */ }) {
                Icon(Icons.Default.Build, contentDescription = "Filter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of profiles
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10) { index ->
                ProfileCard(name = "Person $index")
            }
        }
    }
}

@Composable
fun ProfileCard(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewSwipeScreen() {
    SwipeScreen()
}
