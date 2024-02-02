package com.example.mycityapp.categories

import com.example.mycityapp.R

data class Park( val name : String, val address : String, val rating : Float, val image : Int)

fun getParks(): List<Park> {
    return listOf(
        Park("Central Park", "Address 1", 4.5f, R.drawable.Park),
        Park("Hyde Park", "Address 2", 4.0f, R.drawable.Park),
        Park("Golden Gate Park", "Address 3", 4.5f, R.drawable.Park),
        Park("Stanley Park", "Address 4", 4.0f, R.drawable.Park),
        Park("Vondelpark", "Address 5", 4.5f, R.drawable.Park)
    )
}