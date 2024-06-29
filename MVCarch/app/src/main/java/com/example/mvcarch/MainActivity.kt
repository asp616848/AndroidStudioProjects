package com.example.mvcarch

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvcarch.ui.theme.MVCarchTheme
import java.util.Observable
import java.util.Observer

class MainActivity : ComponentActivity(), Observer {
    var myModel : model? = null
    var button1 : Button? = null
    var button2 : Button? = null
    var button3 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myModel = model()
        myModel?.addObserver(this)

        button1 = findViewById<Button>(R.id.button).apply {
            setOnClickListener { button1Click(it) }
        }
        button2 = findViewById<Button>(R.id.button2).apply {
            setOnClickListener { button2Click(it) }
        }
        button3 = findViewById<Button>(R.id.button3).apply {
            setOnClickListener { button3Click(it) }
        }
    }

    fun button1Click(view : android.view.View) {
        myModel?.setter(0)
    }
    fun button2Click(view : android.view.View) {
        myModel?.setter(1)
    }
    fun button3Click(view : android.view.View) {
        myModel?.setter(2)
    }

    override fun update(o: Observable?, arg: Any?) {
        button1?.text = myModel?.getter(0).toString()
        button2?.text = myModel?.getter(1).toString()
        button3?.text = myModel?.getter(2).toString()
    }
}