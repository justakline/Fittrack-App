package com.example.fittrack

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseDataPoint

class ExerciseListViewModel(): ViewModel() {
    var exercises = mutableStateListOf<Exercise>()


    fun setupExercises(initialExercises: List<Exercise>) {
        exercises.addAll(initialExercises)
    }

    fun getLastFiveSets(exercise: Exercise): List<ExerciseDataPoint> {
        // Assuming each Exercise has a list of ExerciseDataPoints
        // Fetching the last 5 sets
        var returnExercises = exercises.get(exercises.indexOf(exercise)).exerciseDataPoints.takeLast(5)
        return returnExercises
    }
    fun addDataToExercise(exercise: Exercise, data: ExerciseDataPoint) {

        val index = exercises.indexOfFirst { it == exercise }
        if (index != -1) {
            val updatedExercise = exercises[index].copy(
                exerciseDataPoints = (exercises[index].exerciseDataPoints + data).toMutableStateList()
            )
            exercises[index] = updatedExercise
        }
    }

}