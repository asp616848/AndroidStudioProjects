package com.example.simpleadapter

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
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
import com.example.simpleadapter.ui.theme.SimpleAdapterTheme

class MainActivity : ComponentActivity() {
    var fruitNames = arrayOf("Apple", "Banana", "Orange", "Mango", "Papaya")
    var fruitImages = intArrayOf(R.drawable.apple, R.drawable.banana, R.drawable.orange, R.drawable.mango, R.drawable.papaya)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val list = ArrayList<HashMap<String, Any>>()
        for(i in fruitNames.indices) {
            var map : HashMap<String, Any> = HashMap<String, Any>()
            map.put("name", fruitNames[i])
            map.put("image", fruitImages[i])
            list.add(map)
        }
        val adapter = SimpleAdapter(this, list, R.layout.row_layout, arrayOf("name", "image"), intArrayOf(R.id.textView, R.id.imageView))
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this@MainActivity, "You clicked " + fruitNames[position], Toast.LENGTH_SHORT).show()
        }
    }
}