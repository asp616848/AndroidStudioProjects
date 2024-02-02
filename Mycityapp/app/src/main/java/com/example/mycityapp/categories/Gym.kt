package com.example.mycityapp.categories

import com.example.mycityapp.R

data class Gym( val name : String, val address : String, val rating : Float, val image : Int)
fun getGyms(): List<Gym> {
    return listOf(
        Gym("Planet Fitness", "Address 1", 4.5f, R.drawable.Gym),
        Gym("Gold's Gym", "Address 2", 4.0f, R.drawable.Gym),
        Gym("G Y GYM", "Address 3", 4.5f, R.drawable.Gym),
        Gym("Get Fit Universe", "Address 4", 4.0f, R.drawable.Gym),
        Gym("The Kick-Boxing GYM", "Address 5", 4.5f, R.drawable.Gym),
    )
}