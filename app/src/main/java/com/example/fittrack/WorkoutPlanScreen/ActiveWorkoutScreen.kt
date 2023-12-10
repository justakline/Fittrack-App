package com.example.fittrack.WorkoutPlanScreen

//package com.example.fittrack

import WorkoutPlanViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.ExerciseListPopup
import com.example.fittrack.models.Exercise
@Composable
// Screen for managing active workout
fun ActiveWorkoutScreen(allExercises: MutableList<Exercise>, returnToWorkouts: ()->Unit, viewModel: WorkoutPlanViewModel){
    // Check if the user clicked to add an exercise
    if(viewModel.addExerciseClicked){
        // Pop-up for adding exercises to the workout
        ExerciseListPopup(allExercises = allExercises, exitPopup = { viewModel.addExerciseClicked = false },
            addExerciseToWorkout = {exercise ->
                viewModel.currentExercises = viewModel.currentExercises.toMutableList().apply { add(exercise) }
                viewModel.addExerciseClicked = false
            })
    }

    // Column layout for the screen
    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) { //JK
        // Row for the workout header
        Row (horizontalArrangement = Arrangement.SpaceBetween){ //JK
            Icon(Icons.Rounded.Check, contentDescription = "",   modifier = Modifier //JK
                .weight(1f)
                .clickable { returnToWorkouts() },) //JK
            Text( //JK
                modifier = Modifier.weight(5f), //JK
                text = "Default Workout Name", //JK
                fontSize = 22.sp, //JK
                textAlign = TextAlign.Left //JK
            )

        }
        // Box layout for exercise listing
        Box(modifier = Modifier.fillMaxWidth()){ //JK
            // List of exercises
            LazyColumn(){ //JK
                items(viewModel.currentExercises){exercise-> //JK
                    Exercise(exercise, viewModel) //JK
                }
                /* TODO Where the exercises will be */
            }
        }

        /* Buttons for adding and cancelling workout */
        Button(onClick = { viewModel.addExerciseClicked = true }) { //JK
            Text(text = "Add Exercise") //JK
        }
        Button(onClick = { returnToWorkouts() }) { //JK
            Text(text = "Cancel Workout") //JK
        }

    }


}

@Composable
// Displays a single exercise with options to modify
fun Exercise(exercise: Exercise, viewModel: WorkoutPlanViewModel){
    var sets by remember { mutableStateOf(0)} // State to track number of sets

    // Column layout for each exercise
    Column { //JK
        Text(text = "${exercise.name}") //JK
        // Row for displaying set details
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){ //JK
            Text(modifier = Modifier.weight(3f),text = "Set") //JK
            Text(modifier = Modifier.weight(3f),text = "LBS") //JK
            Text(modifier = Modifier.weight(3f),text = "Reps") //JK
            Icon(Icons.Rounded.Delete, contentDescription = "",modifier = Modifier.weight(1f).clickable { //JK
                viewModel.removeExercise(exercise) //JK
            })
        }
        // LazyColumn for exercise data points
        LazyColumn(modifier = Modifier.height((60 + 60 * exercise.exerciseDataPoints.size).dp)) { //JK
            items(exercise.exerciseDataPoints) { dataPoint -> //JK
                ExerciseRow(setNumber = dataPoint.set, onDelete = { //JK
                    // Update the exerciseDataPoints for this specific exercise
                    viewModel.removeSetFromExercise(dataPoint, exercise) //JK
                }, viewModel)
            }
            item {
                Button(modifier = Modifier.fillMaxWidth(), onClick = { //JK
                    viewModel.addSetToExercise(exercise) //JK
                }) {
                    Text(text = "Add Set") //JK
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Row layout for each set within an exercise
fun ExerciseRow(setNumber: Int, onDelete: () -> Unit, viewModel: WorkoutPlanViewModel){
    var lbs by rememberSaveable { mutableStateOf(0) } // State to track pounds
    var reps by rememberSaveable { mutableStateOf(0) } // State to track repetitions
    val default = 0
    //Only a number keyboard is allowed
    val keyBoard = KeyboardOptions(keyboardType = KeyboardType.Number)
    // Row layout for each set
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){ //JK
        // Read-only card for set number
        Card(modifier = Modifier.weight(3f), shape = RoundedCornerShape(10.dp)) { //JK
            TextField("${setNumber}", onValueChange = {}, readOnly = true) //JK
        }
        // Card for entering pounds
        Card(modifier = Modifier.weight(3f),shape = RoundedCornerShape(10.dp)) { //JK
            TextField(lbs.toString(), onValueChange = {s->  if(s.toIntOrNull() != null ) lbs = s.toInt()}, placeholder = {Text("0")}, keyboardOptions = keyBoard) //JK
        }
        // Card for entering reps
        Card(modifier = Modifier.weight(3f),shape = RoundedCornerShape(10.dp)) { //JK
            TextField(reps.toString(), onValueChange = {s->  if(s.toIntOrNull() != null ) reps = s.toInt()}, placeholder = {Text("0")}, keyboardOptions = keyBoard)} //JK
        // Icon for deleting the set
        Icon(Icons.Rounded.Delete, contentDescription = "", modifier = Modifier //JK
            .weight(1f)
            .clickable { onDelete() } ) //JK
    }
}
