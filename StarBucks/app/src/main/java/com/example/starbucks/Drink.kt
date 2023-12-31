package com.example.starbucks

import android.annotation.SuppressLint

class Drink(
    var name: String,
    var description: String,
    var image: Int,
    var price: Double
) {
    companion object {
        val drinks = mutableListOf<Drink>()

        init {
            drinks.add(
                Drink(
                    "Cappuccino",
                    "A warm, soothing cup of steamed milk marked with espresso and finished with a layer of foam",
                    R.drawable.one,
                    199.0
                )
            )
            drinks.add(
                Drink(
                    "Caffè Latte",
                    "Freshly steamed milk with vanilla-flavored syrup marked with espresso and topped with a caramel drizzle for an oh-so-sweet finish",
                    R.drawable.two,
                    399.0
                )
            )
            drinks.add(
                Drink(
                    "Caramel Macchiato",
                    "Freshly steamed milk with vanilla-flavored syrup is marked with espresso and topped with caramel drizzle for an oh-so-sweet finish",
                    R.drawable.three,
                    499.0
                )
            )
            drinks.add(
                Drink(
                    "Caffè Mocha",
                    "Freshly steamed milk with mocha-flavored syrup is marked with espresso and topped with a swirl of whipped cream and drizzle of mocha sauce",
                    R.drawable.four,
                    99.0
                )
            )
        }
    }
    override fun toString(): String {
        return name
    }

    fun getName(): CharSequence? {
        return name
    }

    fun getDescription(): CharSequence? {
        return description
    }

    fun getImageResourceId(): Int {
        return image
    }
}
