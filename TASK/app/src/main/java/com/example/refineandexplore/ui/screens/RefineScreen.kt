import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RefineScreen(
    innverPadding: PaddingValues,
    onBack: () -> Unit,
    onSaveAndExplore: () -> Unit,
    availabilityOptions: List<String>,
    statusOptions: List<String>,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Refine") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innverPadding)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Dropdown(
                options = availabilityOptions,
                label = "Select your Availability"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Dropdown(
                options = statusOptions,
                label = "Add Your Status"
            )

            Spacer(modifier = Modifier.height(16.dp))
            var sliderValue by rememberSaveable { mutableStateOf(0f) }

            Text("Select Hyper Local Distance", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "                                    Selected Distance: ${sliderValue.toInt()} km", style = MaterialTheme.typography.bodySmall)

            Slider(
                value = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                },
                valueRange = 0f..100f,
                steps = 100,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -16.dp),
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            )
            Text(text = " 0 km                                                                                                 100km", style = MaterialTheme.typography.bodySmall, modifier = Modifier.offset(y = -32.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text("Select Purpose", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ToggleButton(text = "Coffee")
                ToggleButton(text = "Business")
                ToggleButton(text = "Hobbies")
                ToggleButton(text = "Friendship")
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                ToggleButton(text = "Movies")
                ToggleButton(text = "Dining")
                ToggleButton(text = "Dating")
                ToggleButton(text = "Matrimony")
            }

            Spacer(modifier = Modifier.weight(1f))

            Row{

                Button(
                    onClick = onSaveAndExplore,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save and Explore", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
private fun Dropdown(
    options: List<String>,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column {
        Text(label)

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedOption+"                                                                             ")
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = "Expand dropdown")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(text = { Text(option) },
                    onClick = {
                        selectedIndex = index
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}



@Composable
private fun ToggleButton(text: String) {
    var selected by remember { mutableStateOf(false) }

    Button(
        onClick = { selected = !selected },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color.Blue else Color.Gray
        ),
        modifier = Modifier
            .padding(horizontal = 1.dp)
            .heightIn(30.dp) // Set a maximum width for the button
    ) {
        Text(text = text, color = Color.White, style = MaterialTheme.typography.labelSmall)
    }
}


@Preview
@Composable
fun PreviewRefineScreen() {
    RefineScreen(
        innverPadding = PaddingValues(),
        onBack = {},
        onSaveAndExplore = {},
        availabilityOptions = listOf("Available", "Busy", "Away"),
        statusOptions = listOf("Online", "Offline")
    )
}