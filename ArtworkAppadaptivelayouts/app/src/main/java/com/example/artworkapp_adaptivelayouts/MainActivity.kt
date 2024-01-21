package com.example.artworkapp_adaptivelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.artworkapp_adaptivelayouts.ui.theme.ArtworkAppadaptivelayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtworkAppadaptivelayoutsTheme {
                displayArtWork()
            }
        }
    }
}

@Composable
fun displayArtWork() {
    var current by remember { mutableStateOf(0) }
    val artWork = remember {
        mutableStateOf(artWorkList[current])
    }

    Column {


        artWorkImg(artWork.value.imageId)

        cardLayout(artWork.value)

        switchButtons(current) {
            current = it
            artWork.value = artWorkList[current]
        }
    }
}

@Composable
fun artWorkImg(imageId: Int, ) {
    Image(painter = painterResource(id = imageId), contentDescription = "This is artwork Image")
}

@Composable
fun cardLayout(artWork: ArtWork) {
    Column {
        Text(text = artWork.title, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface)
        Text(text = artWork.description, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun switchButtons(current: Int, onIndexChanged: (Int) -> Unit) {
    Column {
        Button(onClick = { onIndexChanged((current - 1 + artWorkList.size) % artWorkList.size) }) {
            Text(text = "Previous")
        }
        Button(onClick = { onIndexChanged((current + 1) % artWorkList.size) }) {
            Text(text = "Next")
        }
    }
}

class ArtWork(imageId: Int, title: String, description: String) {
    var imageId: Int = imageId
    var title: String = title
    var description: String = description

    companion object : Saver<ArtWork, Bundle> {
        override fun restore(value: Bundle): ArtWork {
            return ArtWork(
                value.getInt("imageId"),
                value.getString("title")!!,
                value.getString("description")!!
            )
        }

        override fun SaverScope.save(value: ArtWork): Bundle {
            return Bundle().apply {
                putInt("imageId", value.imageId)
                putString("title", value.title)
                putString("description", value.description)
            }
        }
    }
}
private val artWork1 : ArtWork = ArtWork(imageId = R.drawable.first, title = "First by first artist ", description = "This is first artwork")
private val artWork2 : ArtWork = ArtWork(imageId = R.drawable.second, title = "Second by Second Artist", description = "This is second artwork")
private val artWork3 : ArtWork = ArtWork(imageId = R.drawable.third, title = "Third by Third Artist", description = "This is third artwork")

val artWorkList : List<ArtWork> = listOf(artWork1, artWork2, artWork3)

