package com.example.workoutapp

import android.app.Activity
import android.app.ListFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class DayFragment : ListFragment() {
    interface WorkoutListListener {
        fun itemClicked(id: Long)
    }
    lateinit var listener :WorkoutListListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val adapter = ArrayAdapter<String>(
            inflater.context,
            android.R.layout.simple_list_item_1,
            Workout.workouts.map { it.day }
        )
        setListAdapter(adapter)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onAttach(activity: Activity){
        super.onAttach(activity)
        this.listener = activity as WorkoutListListener
    }
    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        if(listener != null){
            listener.itemClicked(id)
        }
    }


}