package com.example.workoutapp

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class WorkoutList : Fragment() {
    private var dayId :Long= 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        if(savedInstanceState != null){
            dayId = savedInstanceState.getLong("dayId")
        }else {
            val ft: FragmentTransaction = childFragmentManager.beginTransaction()
            val stopwatch_frag: Stopwatch
            ft.replace(R.id.stopwatch_container, Stopwatch())
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
        return inflater.inflate(R.layout.fragment_workout_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view: View? = getView()
        val workout: Workout = Workout.workouts[dayId.toInt()]
        if (view != null) {
            val muscles: TextView = view.findViewById(R.id.Muscles)
            val workouts: TextView = view.findViewById(R.id.Workouts)
            muscles.setText(workout.muscles)
            workouts.setText(workout.workout)
        }
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("dayId", dayId)
    }
    fun setDay(id :Long){
        this.dayId = id
    }
}