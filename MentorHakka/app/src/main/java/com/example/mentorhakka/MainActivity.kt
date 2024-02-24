package com.example.mentorhakka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mentorhakka.ui.theme.MentorHakkaTheme
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.generationConfig

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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MentorHakkaTheme {
        Greeting("Android")
    }
}

val model = GenerativeModel(
    "gemini-1.0-pro-001",
    // Retrieve API key as an environmental variable defined in a Build Configuration
    // see https://github.com/google/secrets-gradle-plugin for further instructions
    "AIzaSyDeh_2cVlwjg3HGhXB0BfxAjdb78ZRE_UQ",
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

val chatHistory = listOf(
)

val chat = model.startChat(chatHistory)
val response = chat.sendMessage("YOUR_USER_INPUT")

// Get the first text part of the first candidate
print(response.text)
// Alternatively
print(response.candidates.first().content.parts.first().asTextOrNull())