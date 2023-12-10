package com.example.fittrack.models

import java.util.Date


//This is one data Point
data class ExerciseDataPoint (
    val exercise: Exercise,
    val lbs: Int,
    val set: Int,
    val rep: Int,
//    val Date: Date
    )