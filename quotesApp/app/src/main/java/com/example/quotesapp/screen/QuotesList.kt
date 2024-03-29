package com.example.quotesapp.screen

import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import models.DataManager
import models.dataClass



@Composable
fun QuotesList(quote : dataClass, onClick :(quote: dataClass) -> Unit){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) ,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(quote) }
    ){
        Row(modifier = Modifier.padding(16.dp)) {
            Image( imageVector = Icons.Filled.FormatQuote,
                alignment = Alignment.TopStart,
                contentDescription = "Quote",
                modifier = Modifier
                    .size(48.dp)
                    .rotate(180f))
            Spacer(modifier = Modifier.size(10.dp))

            Column {
                Text(text = quote.text ,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 8.dp
                        ),
                    style = MaterialTheme.typography.bodyLarge)

                Box(modifier = Modifier
                    .background(color = Color.Gray)
                    .fillMaxWidth(0.5f)
                    .height(2.dp)
                )

                Text(text = quote.author,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
@Composable
fun QuotesDetails(Quote : dataClass){

    BackHandler {
        DataManager.switchPage(Quote)
    }

    Box(contentAlignment = Alignment.Center ,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Brush.sweepGradient(listOf(Color.Red, Color.Blue)))) {

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) ,
            modifier = Modifier.padding(8.dp)
        ){
            Column(modifier = Modifier.padding(16.dp)) {
                Image( imageVector = Icons.Filled.FormatQuote,
                    alignment = Alignment.TopStart,
                    contentDescription = "Quote",
                    modifier = Modifier
                        .size(60.dp)
                        .rotate(180f))

                Text(text = Quote.text,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 20.dp
                        ),
                    style = MaterialTheme.typography.headlineLarge)

                LinearProgressIndicator(
                    progress = 0.5f,
                    color = Color(0xFF6200EE),
                    modifier = Modifier
                        .fillMaxWidth()
                        .blur(9.dp)
                        .padding(
                            bottom = 20.dp
                        )
                        .height(5.dp)
                )


                Text(text = Quote.author ,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodyMedium)

            }
        }
    }
}