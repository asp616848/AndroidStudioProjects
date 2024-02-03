package com.example.mycityapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycityapp.categories.AllRecClass
import com.example.mycityapp.categories.getGyms
import com.example.mycityapp.categories.getParks
import com.example.mycityapp.categories.getPizza
import com.example.mycityapp.categories.getTeaShops

@Composable
fun RecomendationsScreen(navController: NavController, index: Int) {

    val Recomendations = when(index){
        0 -> getTeaShops()
        1 -> getParks()
        2 -> getGyms()
        else -> getPizza()
    }

    Card(){
        LazyColumn {
            items(Recomendations.size) { recommendation ->
                InternalRecScreen(Recommendation = Recomendations[index])
            }
        }
    }
}

@Composable
fun InternalRecScreen(Recommendation: AllRecClass) {
    Card{
        Column {
            Image(painter = painterResource(id =Recommendation.image ) , contentDescription = Recommendation.name)

            Spacer(modifier = androidx.compose.ui.Modifier.padding(12.dp))
            Row{
                Text(text = Recommendation.name)
                Text(text = "This place is rated ${Recommendation.rating}")
            }
        }
    }
}

