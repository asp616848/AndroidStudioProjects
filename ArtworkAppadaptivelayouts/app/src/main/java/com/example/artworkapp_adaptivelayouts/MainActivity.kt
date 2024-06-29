package com.example.artworkapp_adaptivelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
    val windowInfo = rememberWindowInfo()


    var current by remember { mutableStateOf(0) }
    val artWork = rememberSaveable(stateSaver = ArtWork.ArtWorkSaver) {
        mutableStateOf(artWorkList[current])
    }
    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.compact) {
        Column {


            artWorkImg(artWork.value.imageId)
            Spacer(modifier = Modifier.height(106.dp))

            cardLayout(artWork.value)
            switchButtons(current) {
                current = it
                artWork.value = artWorkList[current]
            }
        }
    }
    else{
        Row{

            artWorkImg(artWork.value.imageId)

            Spacer(modifier = Modifier.width(106.dp))
            Column {

                cardLayout(artWork.value)

                switchButtons(current) {
                    current = it
                    artWork.value = artWorkList[current]
                }
            }
        }
    }
}

@Composable
fun artWorkImg(imageId: Int, ) {
    Image(painter = painterResource(id = imageId), contentDescription = "This is artwork Image")
}

@Composable
fun cardLayout(artWork: ArtWork) {
    Card{Column {
        Text(text = artWork.title, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface)
        Text(text = artWork.description, style = MaterialTheme.typography.bodyMedium)
    }}
}

@Composable
fun switchButtons(current: Int, onIndexChanged: (Int) -> Unit) {
    Row {
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

    object ArtWorkSaver : Saver<ArtWork, Map<String, Any>>{
        override fun restore(value: Map<String, Any>): ArtWork? {
            return ArtWork(
                imageId = value["imageId"] as Int,
                title = value["title"] as String,
                description = value["description"] as String
            )   
        }

        override fun SaverScope.save(value: ArtWork): Map<String, Any>? {
            return mapOf(
                "imageId" to value.imageId,
                "title" to value.title,
                "description" to value.description
            )
        }

    }
}
private val artWork1 : ArtWork = ArtWork(imageId = R.drawable.first, title = "First by first artist ", description = "This is first artwork")
private val artWork2 : ArtWork = ArtWork(imageId = R.drawable.second, title = "Second by Second Artist", description = "This is second artwork")
private val artWork3 : ArtWork = ArtWork(imageId = R.drawable.third, title = "Third by Third Artist", description = "This is third artwork")

val artWorkList : List<ArtWork> = listOf(artWork1, artWork2, artWork3)



//windows size based edit starts here

@Composable
fun rememberWindowInfo(): WindowInfo{
    val configuration = LocalConfiguration.current
    return WindowInfo(screenHeightInfo = when {
        configuration.screenHeightDp < 400 -> WindowInfo.WindowType.compact
        configuration.screenHeightDp < 600 -> WindowInfo.WindowType.medium
        else -> WindowInfo.WindowType.large
    }
        , screenWidthInfo = when {
            configuration.screenWidthDp < 600-> WindowInfo.WindowType.compact
            configuration.screenWidthDp < 800 -> WindowInfo.WindowType.medium
            else -> WindowInfo.WindowType.large
        }
        , width = configuration.screenWidthDp
        ,height = configuration.screenHeightDp
    )
}


data class WindowInfo(
    val screenWidthInfo: WindowType, val screenHeightInfo: WindowType,
    val width: Int, val height: Int){
    sealed class WindowType{
        object compact :WindowType()
        object medium :WindowType()
        object large :WindowType()
    }
}
