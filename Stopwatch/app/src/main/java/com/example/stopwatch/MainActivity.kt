package com.example.stopwatch

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {
    var isRunning = false
    private lateinit var binding: ActivityMainBinding // Declare the binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Inflate the layout
        setContentView(binding.root)
        binding.imageview.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog)
            var numberpicker=dialog.findViewById<NumberPicker>(R.id.numberpicker)
            numberpicker.minValue=0
            numberpicker.maxValue=5
            dialog.findViewById<<Button>(R.id.set_time).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
        binding.run.setOnClickListener {
            if(isRunning){
                isRunning = false

            }
            else{

            }
        }
    }
}