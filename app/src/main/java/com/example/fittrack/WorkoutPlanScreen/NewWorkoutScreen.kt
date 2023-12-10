package com.example.fittrack.WorkoutPlanScreen

import WorkoutPlanViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.ExerciseListPopup
import com.example.fittrack.models.Exercise

@Composable
// Screen for creating a new workout
fun NewWorkoutScreen (allExercises: MutableList<Exercise>, returnToWorkouts: ()->Unit, viewModel: WorkoutPlanViewModel){
    // Check if the user clicked to add an exercise
    if(viewModel.addExerciseClicked){
        // Pop-up for adding exercises to the workout
        ExerciseListPopup(allExercises = allExercises, exitPopup = { viewModel.addExerciseClicked = false }, //JK
            addExerciseToWorkout = {exercise ->  //JK
                viewModel.currentExercises = viewModel.currentExercises.toMutableList().apply { add(exercise) } //JK
                viewModel.addExerciseClicked = false //JK
            }) //JK
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
            ) //JK
        } //JK
        // Box layout for exercise listing
        Box(modifier = Modifier.fillMaxWidth()){ //JK
            // List of exercises
            LazyColumn(){ //JK
                items(viewModel.currentExercises){exercise-> //JK
                    Exercise(exercise, viewModel) //JK
                } //JK
                /* TODO Where the exercises will be */
            } //JK
        } //JK
        /* Buttons for adding and cancelling workout */
        Button(onClick = { viewModel.addExerciseClicked = true }) { //JK
            Text(text = "Add Exercise") //JK
        } //JK
        Button(onClick = { returnToWorkouts() }) { //JK
            Text(text = "Cancel Workout") //JK
        } //JK
    } //JK
}
