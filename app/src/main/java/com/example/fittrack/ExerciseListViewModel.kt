package com.example.fittrack

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseDataPoint

class ExerciseListViewModel(): ViewModel() {
    // List to hold exercises
    var exercises = mutableStateListOf<Exercise>() //JK

    // Function to set up initial exercises
    fun setupExercises(initialExercises: List<Exercise>) {
        exercises.addAll(initialExercises) // Add all the initial exercises to the list //JK
    }

    // Function to get the last five sets of a given exercise
    fun getLastFiveSets(exercise: Exercise): List<ExerciseDataPoint> {
        // Assuming each Exercise has a list of ExerciseDataPoints
        // Fetching the last 5 sets
        var returnExercises = exercises.get(exercises.indexOf(exercise)).exerciseDataPoints.takeLast(5) //JK
        return returnExercises // Return the last 5 sets //JK
    }

    // Function to add new data to a specific exercise
    fun addDataToExercise(exercise: Exercise, data: ExerciseDataPoint) {
        // Find the index of the exercise
        val index = exercises.indexOfFirst { it == exercise } //JK
        if (index != -1) { // Check if the exercise exists in the list //JK
            // Create an updated exercise with the new data point added
            val updatedExercise = exercises[index].copy(
                exerciseDataPoints = (exercises[index].exerciseDataPoints + data).toMutableStateList() //JK
            )
            exercises[index] = updatedExercise // Update the exercise in the list //JK
        }
    }
}