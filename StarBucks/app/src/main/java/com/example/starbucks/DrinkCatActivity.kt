package com.example.starbucks

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

class DrinkCatActivity : ListActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listDrinks :ListView = listView
        val listAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Drink.drinks
        )
        listDrinks.adapter = listAdapter
    }
}