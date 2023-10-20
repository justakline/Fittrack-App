package com.example.fittrack.models

//An exercise is a name, an type, and bodypart
data class Exercise(
    val name: String,
    val type:ExerciseType,
    val bodyPart: BodyPart
)