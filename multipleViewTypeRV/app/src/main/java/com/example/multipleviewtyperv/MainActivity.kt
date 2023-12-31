package com.example.multipleviewtyperv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewtyperv.ui.theme.MultipleViewTypeRVTheme

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
        val RV = findViewById<RecyclerView>(R.id.RV) as RecyclerView
        RV.layoutManager = LinearLayoutManager(this)
        val adapter = adapterClass()
        adapter.itemsList = itemsList
        RV.adapter = adapter
    }




}
