package com.example.fittrack.models

import java.util.Date

//Multiple exercises makeup one workout
data class WorkoutData (
    val exerciseData: MutableList<ExerciseData>,
    val date: Date
)