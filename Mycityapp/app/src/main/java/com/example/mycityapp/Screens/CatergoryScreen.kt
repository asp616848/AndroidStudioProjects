package com.example.mycityapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mycityapp.categories.CategoryClass

@Composable
fun CatergoryScreen( navController: NavController){
    Card{
        LazyColumn {
            items(CategoryClass.categories.size) { index ->
                Text(text = CategoryClass.categories[index], modifier = androidx.compose.ui.Modifier.clickable {
                    navigateToCategoryDetail(navController, index)
                })
            }
        }
    }
}

fun navigateToCategoryDetail(navController: NavController, index: Int) {
    navController.navigate("category_detail_screen/$index")

}