package com.example.fittrack


//This allows us to switch between screens easily
sealed class Routes(val route: String){

    object ExerciseListScreen: Routes("exerciselist")
    object ProgressScreen: Routes("progress")
    object WorkoutPlanScreen: Routes("workoutplan")
}
