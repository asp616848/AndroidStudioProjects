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

class DetailActivity : Activity() {
    public final var EXTRA_WORKOUT_ID: String = "id"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val frag : WorkoutList = getFragmentManager().findFragmentById(R.id.detail_frag) as WorkoutList
        val workoutId = intent.getLongExtra(EXTRA_WORKOUT_ID, 0)
        frag.setDay(workoutId)
    }
}
