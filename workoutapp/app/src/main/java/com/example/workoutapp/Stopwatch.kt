package com.example.workoutapp

import android.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Stopwatch : Fragment(), View.OnClickListener { //implements on click listener because onClick from layout calls the method in mainactivity but the method doesn't exist their
    private var seconds : Int = 0
    private var running : Boolean = false
    private var wasRunning : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
            if(wasRunning){
                running = true
            }
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.start_button -> onClickStart(v)
            R.id.stop_button -> onClickStop(v)
            R.id.reset_button -> onClickReset(v)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        runTimer(layout)
        val startButton = layout.findViewById(R.id.start_button) as TextView
        startButton.setOnClickListener(this)
        val stopButton = layout.findViewById(R.id.stop_button) as TextView
        stopButton.setOnClickListener(this)
        val resetButton = layout.findViewById(R.id.reset_button) as TextView
        resetButton.setOnClickListener(this)
        return layout
    }
    override fun onPause(){
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if(wasRunning){
            running = true
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }
    public fun onClickStart (view :View) {
        running = true
    }
    public fun onClickStop (view:View){
        running = false
    }
    public fun onClickReset (view:View){
        running = false
        seconds = 0
    }
    private fun runTimer(view: View){
        val timeView = view.findViewById(R.id.time_view) as TextView
        val handler = Handler()
        handler.post(object : Runnable{
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time //set the text of the TextView
                if(running){
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}