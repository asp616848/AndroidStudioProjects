package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.quotesapp.screen.QuotesDetails
import com.example.quotesapp.screen.listScreen
import com.example.quotesapp.ui.theme.QuotesAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.DataManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            DataManager.loadAssetsFromFile(this@MainActivity)
        }

        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    if (DataManager.isDataLoaded.value) {
        if(DataManager.currentPage.value == pages.LIST){
            listScreen(data = DataManager.data){
                DataManager.switchPage(it)
            }
        }
        else{
            QuotesDetails(Quote = DataManager.currentQuote!!)
        }
    }
    else{
        Box{
            Text(text = "Loading...")
        }
    }
}

enum class pages{
    LIST,
    DETAIL
}
