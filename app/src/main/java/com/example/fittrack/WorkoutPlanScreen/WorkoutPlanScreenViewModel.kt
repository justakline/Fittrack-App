import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.fittrack.ExerciseListViewModel
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseDataPoint
class WorkoutPlanViewModel() : ViewModel() {
    // State variables to track UI interactions and current workout data
    var popupClicked by mutableStateOf(false) // Tracks if the popup has been clicked
    var currentlyWorkingOut by mutableStateOf(false) // Tracks if the user is currently working out
    var addingNewWorkout by mutableStateOf(false) // Tracks if adding a new workout
    var currentExercises by mutableStateOf(mutableListOf<Exercise>()) // List of current exercises in the workout
    var addExerciseClicked by mutableStateOf(false) // Tracks if the add exercise button has been clicked

    // Function to add a set to a specific exercise
    fun addSetToExercise(exercise: Exercise) {
        // Update the list of current exercises
        currentExercises = currentExercises.map { e ->
            if (e === exercise) {
                // Update only the targeted exercise with a new set
                e.copy(exerciseDataPoints = (exercise.exerciseDataPoints + ExerciseDataPoint(exercise, 0, exercise.exerciseDataPoints.size + 1, 0)).toMutableList() )
            } else {
                // Leave other exercises unchanged
                e
            }
        }.toMutableList()
    }

    // Function to remove a set from a specific exercise
    fun removeSetFromExercise(dataPoint: ExerciseDataPoint, exercise: Exercise) {
        // Update the list of current exercises
        currentExercises = currentExercises.map { e ->
            if (e == exercise) {
                // If it's the matching exercise, filter out the set to be removed
                e.copy(exerciseDataPoints = e.exerciseDataPoints.filter { it != dataPoint }.toMutableList())
            } else {
                // For all other exercises, leave them unchanged
                e
            }
        }.toMutableList()
    }

    // Function to remove an entire exercise
    fun removeExercise(exercise: Exercise){
        // Filter out the exercise to be removed from the current exercises list
        currentExercises = currentExercises.filter { it != exercise }.toMutableList()
    }
}
