package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.view.View
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicapp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() , View.OnClickListener {
    private var start: Button? = null
    private var stop: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById<View>(R.id.button1) as Button
        stop = findViewById<View>(R.id.button2) as Button
        start!!.setOnClickListener(this)
        stop!!.setOnClickListener(this)
    }
    override fun onClick(view: View) {

        // process to be performed
        // if start button is clicked
        if (view === start) {
            // starting the service
            startService(Intent(this, NewService::class.java))
        }
        // process to be performed
        // if stop button is clicked
        else if (view === stop) {

            // stopping the service
            stopService(Intent(this, NewService::class.java))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicAppTheme {
        Greeting("Android")
    }
}