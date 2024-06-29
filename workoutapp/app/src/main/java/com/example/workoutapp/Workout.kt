package com.example.workoutapp

class Workout {
    lateinit var muscles :String
    lateinit var workout :String
    lateinit var day :String
    companion object{
        val workouts = arrayOf(
            Workout().apply {
                day = "Monday"
                muscles = "Chest"
                workout = "Bench Press \n Incline Bench Press \n Decline Bench Press \n Dumbbell Fly \n Cable Fly"
            },
            Workout().apply {
                day = "Tuesday"
                muscles = "Back"
                workout = "Pull Ups \n Chin Ups \n Lat Pull Down \n Seated Row \n Bent Over Row"
            },
            Workout().apply {
                day = "Wednesday"
                muscles = "Legs"
                workout = "Squats \n Leg Press \n Leg Extension \n Leg Curl \n Calf Raise"
            },
            Workout().apply {
                day = "Thursday"
                muscles = "Arms"
                workout = "Barbell Curl \n Dumbbell Curl \n Hammer Curl \n Tricep Extension \n Tricep Pushdown"
            },
            Workout().apply {
                day = "Friday"
                muscles = "Abs and flexibility"
                workout = "Situps \n Crunches \n Planks \n Stretching \n Yoga"
            },
            Workout().apply {
                day = "Saturday"
                muscles = "Shoulders"
                workout = "Shoulder Press \n Lateral Raise \n Front Raise \n Rear Delt Fly \n Shrugs"
            },
            Workout().apply {
                day = "Sunday"
                muscles = "Rest Day :("
                workout = "Meditation"
            })
    }
}