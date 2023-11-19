package com.example.fittrack.models

import java.util.Date
//Multiple datapoints make up one exercise
data class ExerciseData(
    val exercise: Exercise,
    val exerciseDataPoints: MutableList<ExerciseDataPoint>
)