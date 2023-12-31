package com.example.starbucks

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DrinkActivity : Activity() {
    companion object {
        const val EXTRA_DRINKNO = "drinkNo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        // Get the drink from the intent
        val drinkNo = intent?.extras?.getInt(EXTRA_DRINKNO)
        val drink = Drink.drinks[drinkNo ?: 0] // Defaulting to 0 if drinkNo is null

        // Populate the drink image
        val photo = findViewById<ImageView>(R.id.photo)
        photo.setImageResource(drink.getImageResourceId())
        photo.contentDescription = drink.getName()

        // Populate the drink name
        val name = findViewById<TextView>(R.id.name)
        name.text = drink.getName()

        // Populate the drink description
        val description = findViewById<TextView>(R.id.description)
        description.text = drink.getDescription()
    }
}
