package com.example.quotesapp.screen

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import models.dataClass


@Composable
fun listScreen(data : Array<dataClass>, onClick :(quote : dataClass) -> Unit){
    Column {
        Text(
            text = "Quotes App", textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(androidx.compose.ui.graphics.Color.Green, androidx.compose.ui.graphics.Color.Red)))
                .height(20.dp)
                .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally), // Move this line here
            color = androidx.compose.ui.graphics.Color.Black
        )
        Box(modifier = Modifier.height(3.dp)
            .background(color = androidx.compose.ui.graphics.Color.Black)
            .background(Brush.linearGradient(listOf(androidx.compose.ui.graphics.Color.Green, androidx.compose.ui.graphics.Color.Red)))
            .fillMaxWidth()
            .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
            .alpha(0.5f)
            .blur(10.dp))

        Spacer(modifier = Modifier.size(10.dp))

        LazyColumn() {
            items(data.size) {
                QuotesList(quote = data[it], onClick)
            }
        }
    }
}