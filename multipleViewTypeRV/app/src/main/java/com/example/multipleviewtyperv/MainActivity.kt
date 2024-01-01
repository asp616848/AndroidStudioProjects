package com.example.multipleviewtyperv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var itemsList = mutableListOf<itemClass>()
        itemsList.add(itemClass("Text one", 0))
        itemsList.add(itemClass("Text one", "Text two", androidx.core.R.drawable.ic_call_answer_video_low, 1))
        itemsList.add(itemClass("single", 0))
        itemsList.add(itemClass("dou", "ble", androidx.constraintlayout.widget.R.drawable.abc_ic_arrow_drop_right_black_24dp, 1))
        itemsList.add(itemClass("Text one", 0))
        itemsList.add(itemClass("Text one", "Text two", androidx.core.R.drawable.notification_bg_low_pressed, 1))
        itemsList.add(itemClass("single", 0))
        val recyclerView = findViewById<RecyclerView>(R.id.RV) as RecyclerView ?:throw NullPointerException("RecycleView Could not be found")
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = adapterClass()
        adapter.itemsList = itemsList
        recyclerView.adapter = adapter
    }




}
