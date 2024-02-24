package com.example.mentorhakka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentorhakka.ui.theme.MentorHakkaTheme
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.MainScope
import androidx.compose.material3.DropdownMenuItem
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentorHakkaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenHome()
                }
            }
        }
        val scope = MainScope() // Or any other appropriate coroutine scope

        scope.launch {
            val chat = model.startChat(chatHistory)
            val response = chat.sendMessage("YOUR_USER_INPUT")
            // Get the first text part of the first candidate
            print(response.text)
            // Alternatively
            print(response.candidates.first().content.parts.first().asTextOrNull())
        }
    }
}

@Composable
fun ScreenHome(modifier: Modifier = Modifier) {
    var journalEntry by remember { mutableStateOf("") }
    var selectedAim by remember { mutableStateOf("") }
    val aims = listOf("Gym", "Academics", "Coding", "Other")

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = journalEntry,
            onValueChange = { journalEntry = it },
            label = { Text("Journal Entry") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        DropdownMenu(
            expanded = selectedAim.isNotEmpty(),
            onDismissRequest = { selectedAim = "" },
            modifier = Modifier.fillMaxWidth()
        ) {
            aims.forEach { aim ->
                DropdownMenuItem(onClick = { selectedAim = aim }) {
                    Text(aim)
                }
            }
        }
        if (selectedAim.isNotEmpty() && journalEntry.isNotEmpty()) {
            Button(onClick = {
                val formattedInput = "$journalEntry with the aim of $selectedAim"
                // Call the API with formattedInput
                // Update the UI with the response from the API
            }) {
                Text("Get Guidance")
            }
        }
    }
}


val model = GenerativeModel(
    "gemini-1.0-pro-001",
    // Retrieve API key as an environmental variable defined in a Build Configuration
    // see https://github.com/google/secrets-gradle-plugin for further instructions
    "",
    generationConfig = generationConfig {
        temperature = 0.9f
        topK = 1
        topP = 1f
        maxOutputTokens = 2048
    },
    safetySettings = listOf(
        SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
        SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
        SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
        SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
    ),
)

val chatHistory = listOf<Content>(
)