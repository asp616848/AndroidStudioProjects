package com.example.mycityapp.categories

import com.example.mycityapp.R

data class TeaShops ( val name : String, val address : String, val rating : Float, val image : Int)
fun getTeaShops(): List<TeaShops> {
    return listOf(
        TeaShops("Tea Time", "Address 1", 4.5f, R.drawable.TeaShop),
        TeaShops("Tea House", "Address 2", 4.0f, R.drawable.TeaShop),
        TeaShops("Tea Brawahalla", "Address 3", 4.5f, R.drawable.TeaShop),
        TeaShops("Valhala TeaTeas", "Address 4", 4.0f, R.drawable.TeaShop),
        TeaShops("Cafe Coffee Day", "Address 5", 4.5f, R.drawable.TeaShop)
    )
}