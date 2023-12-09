package com.example.fittrack.WorkoutPlanScreen
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseData
import com.example.fittrack.models.ExerciseDataPoint

class ActiveWorkoutScreenViewModel : ViewModel() {
    var currentExercises by   mutableStateOf(mutableListOf<Exercise>())
    var addExerciseClicked by   mutableStateOf(false)

    fun addSetToExercise(exercise: Exercise) {
        currentExercises = currentExercises.map { e ->
            if (e === exercise) {
                // Update only the targeted exercise
                e.copy(exerciseDataPoints = (exercise.exerciseDataPoints + ExerciseDataPoint(exercise, 0, exercise.exerciseDataPoints.size + 1, 0)).toMutableList() )
            } else {
                // Leave other exercises unchanged
                e
            }
        }.toMutableList()
    }

    fun removeSetFromExercise(dataPoint: ExerciseDataPoint, exercise: Exercise) {
        currentExercises = currentExercises.map { e ->
            if (e == exercise) {
                // If it's the matching exercise, filter out the data point to be removed
                e.copy(exerciseDataPoints = e.exerciseDataPoints.filter { it != dataPoint }.toMutableList())
            } else {
                // For all other exercises, leave them unchanged
                e
            }
        }.toMutableList()
    }
    fun removeExercise(exercise: Exercise){
        currentExercises = currentExercises.filter { it != exercise }.toMutableList()
    }

//    fun addExercise(exercise: Exercise) {
//        currentExercises.add(Exercise()ExerciseData(exercise, mutableListOf()))
//    }

//    fun removeSetToExercise(exercise: Exercise, dataPoint: ExerciseDataPoint) {
//        currentExercises = currentExercises.filter { it ==exercise }.toMutableList()
//        exercise.exerciseDataPoints.remove(dataPoint)
//        currentExercises = currentExercises.a
//
//
//    }
}
