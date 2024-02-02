package com.example.mycityapp.categories

import com.example.mycityapp.R

data class Pizza ( val name : String, val address : String, val rating : Float, val image : Int)
fun getPizza(): List<Pizza> {
    return listOf(
        Pizza("Pizza Hut", "Address 1", 4.5f, R.drawable.Pizza),
        Pizza("Domino's Pizza", "Address 2", 4.0f, R.drawable.Pizza),
        Pizza("Papa John's Pizza", "Address 3", 4.5f, R.drawable.Pizza),
        Pizza("Little Caesars Pizza", "Address 4", 4.0f, R.drawable.Pizza),
        Pizza("Papa Murphy's Pizza", "Address 5", 4.5f, R.drawable.Pizza)
    )
}