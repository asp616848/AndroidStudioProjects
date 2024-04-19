import android.widget.Toast
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.refineandexplore.R
import com.example.refineandexplore.viewModel
import com.google.accompanist.pager.*
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipeScreen(viewModel: viewModel, innerPadding: PaddingValues = PaddingValues()) {
    var currentTab by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("Personal", "Service", "Businesses")

    Column(modifier = Modifier.padding(innerPadding)
        .pointerInput(Unit){
            detectHorizontalDragGestures{
                change, dragAmount ->
                if (dragAmount < 0){
                    currentTab += 1
                } else {
                    currentTab -= 1
                }
            }
        }) {

        // Top bar showing current screen
        TabRow(selectedTabIndex = currentTab ?: 0) {
            viewModel.updatePage(currentTab ?: 0)
            tabs.forEachIndexed { index, text ->
                Tab(
                    text = { Text(text) },
                    selected = currentTab == index.toInt(),
                    onClick = {
                        currentTab = index
                    }
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
        if (currentTab == 0) {
            PersonalScreen()
        } else if (currentTab == 1) {
            ServiceScreen()
        } else {
            BusinessesScreen()
        }
    }
}


@Composable
fun PersonalScreen() {
    LazyColumn {
        items(10) { index ->
            ProfileCard(
                name = "Person $index",
                designation = "Designation $index",
                distanceKm = index,
                profileScore = index * 10,
                likings = listOf("Liking 1", "Liking 2", "Liking 3"),
                bio = "Bio $index")
        }
    }
}

@Composable
fun ServiceScreen() {
    Text(text = "Service Screen")
}

@Composable
fun BusinessesScreen() {
    Text(text = "Businesses Screen")
}

@Composable
fun ProfileCard(
    name: String,
    designation: String,
    distanceKm: Int,
    profileScore: Int,
    likings: List<String>,
    bio: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.TwoTone.Face, // Replace R.drawable.person_icon with the actual resource ID for your person icon
                    contentDescription = "Person Icon",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleMedium)
                    Text(text = designation, color = Color.DarkGray, fontSize = 12.sp)
                    Text(text = "$distanceKm km away", color = Color.Gray, fontSize = 10.sp)
                    Text(text = "Profile Score: $profileScore", color = Color.Gray, fontSize = 9.sp)
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Button(onClick = { /* Handle button click */ }) {
                        Icon(Icons.Filled.Add, contentDescription = "Invite")
                        Text("Invite")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Likings: ")
                likings.forEach { liking ->
                    Text(text = "$liking, ")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Bio: $bio")
        }

    }
}




@Preview
@Composable
fun PreviewSwipeScreen() {
    SwipeScreen(viewModel())
}
