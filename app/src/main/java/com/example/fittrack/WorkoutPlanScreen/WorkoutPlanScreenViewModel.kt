import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.fittrack.ExerciseListViewModel
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseDataPoint

class WorkoutPlanViewModel() : ViewModel() {
    var popupClicked by mutableStateOf(false)
    var currentlyWorkingOut by mutableStateOf(false)
    var addingNewWorkout by mutableStateOf(false)
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
}