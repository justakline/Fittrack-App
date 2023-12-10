package com.example.fittrack.models

//An exercise is a name, an type, and bodypart
data class Exercise( //JK
    var name: String,
    var type:ExerciseType,
    var bodyPart: BodyPart,
    var exerciseDataPoints: MutableList<ExerciseDataPoint>
)