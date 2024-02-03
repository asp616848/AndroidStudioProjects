package com.example.mycityapp.categories

import com.example.mycityapp.R

object CategoryClass {
    val categories = listOf(
        "Tea Shops",
        "Parks",
        "Gyms",
        "Pizza Outlets"
    )
}

sealed class AllRecClass {
    abstract val name: String
    abstract val address: String
    abstract val rating: Float
    abstract val image: Int
}

data class Gym(
    override val name: String,
    override val address: String,
    override val rating: Float,
    override val image: Int
) : AllRecClass()

data class Park(
    override val name: String,
    override val address: String,
    override val rating: Float,
    override val image: Int
) : AllRecClass()

data class Pizza(
    override val name: String,
    override val address: String,
    override val rating: Float,
    override val image: Int
) : AllRecClass()

data class TeaShops(
    override val name: String,
    override val address: String,
    override val rating: Float,
    override val image: Int
) : AllRecClass()

fun getTeaShops(): List<TeaShops> {
    return listOf(
        TeaShops("Tea Time", "Address 1", 4.5f, R.drawable.teashop),
        TeaShops("Tea House", "Address 2", 4.0f, R.drawable.teashop),
        TeaShops("Tea Brawahalla", "Address 3", 4.5f, R.drawable.teashop),
        TeaShops("Valhala TeaTeas", "Address 4", 4.0f, R.drawable.teashop),
        TeaShops("Cafe Coffee Day", "Address 5", 4.5f, R.drawable.teashop)
    )
}

fun getPizza(): List<Pizza> {
    return listOf(
        Pizza("Pizza Hut", "Address 1", 4.5f, R.drawable.pizza),
        Pizza("Domino's Pizza", "Address 2", 4.0f, R.drawable.pizza),
        Pizza("Papa John's Pizza", "Address 3", 4.5f, R.drawable.pizza),
        Pizza("Little Caesars Pizza", "Address 4", 4.0f, R.drawable.pizza),
        Pizza("Papa Murphy's Pizza", "Address 5", 4.5f, R.drawable.pizza)
    )
}

fun getParks(): List<Park> {
    return listOf(
        Park("Central Park", "Address 1", 4.5f, R.drawable.park),
        Park("Hyde Park", "Address 2", 4.0f, R.drawable.park),
        Park("Golden Gate Park", "Address 3", 4.5f, R.drawable.park),
        Park("Stanley Park", "Address 4", 4.0f, R.drawable.park),
        Park("Vondelpark", "Address 5", 4.5f, R.drawable.park)
    )
}

fun getGyms(): List<Gym> {
    return listOf(
        Gym("Planet Fitness", "Address 1", 4.5f, R.drawable.gym),
        Gym("Gold's Gym", "Address 2", 4.0f, R.drawable.gym),
        Gym("G Y GYM", "Address 3", 4.5f, R.drawable.gym),
        Gym("Get Fit Universe", "Address 4", 4.0f, R.drawable.gym),
        Gym("The Kick-Boxing GYM", "Address 5", 4.5f, R.drawable.gym),
    )
}
