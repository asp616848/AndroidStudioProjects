package com.example.workoutapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import android.app.FragmentTransaction
import android.content.Intent
import android.view.View

class MainActivity : Activity(), DayFragment.WorkoutListListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val frag : WorkoutList = getFragmentManager().findFragmentById(R.id.workout_list) as WorkoutList
        //frag.setDay(intent.getLongExtra(WorkoutList.EXTRA_DAY_ID, 0))
    }
    override fun itemClicked(id: Long) {
       var fragmentContainer : View? = findViewById(R.id.fragment_day_container)
        if(fragmentContainer != null) {
            val ft: FragmentTransaction = getFragmentManager().beginTransaction()
            val list = WorkoutList().apply { setDay(id) }
            ft.replace(R.id.fragment_day_container, list)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
        else{
            val intent:Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity().EXTRA_WORKOUT_ID, id)
            startActivity(intent)
        }
    }
}