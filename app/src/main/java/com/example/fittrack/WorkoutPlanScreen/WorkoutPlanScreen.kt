package com.example.fittrack.WorkoutPlanScreen

import WorkoutPlanViewModel
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fittrack.ExerciseListViewModel
import com.example.fittrack.models.Exercise
@Composable
// Screen for displaying and managing workout plans
fun WorkoutPlanScreen(allExercises: MutableList<Exercise>, exerciseList: ExerciseListViewModel){
    // Initialize the WorkoutPlanViewModel
    val viewModel: WorkoutPlanViewModel = viewModel() //JK

    // States for popup and workout status
    var popupClicked by remember {mutableStateOf(false)} //JK
    var currentlyWorkingOut by remember{ mutableStateOf(false) } //JK

    // Check if the user is currently working out
    if(viewModel.currentlyWorkingOut){ //JK
        // Active workout screen
        ActiveWorkoutScreen(allExercises = allExercises, { //JK
            viewModel.popupClicked = false //JK
            viewModel.currentlyWorkingOut = false //JK
        }, viewModel) //JK
    } else if(viewModel.addingNewWorkout){ //JK
        // Screen for adding a new workout
        NewWorkoutScreen(allExercises = allExercises, { //JK
            viewModel.addingNewWorkout = false //JK
        }, viewModel) //JK

    } else { //JK
        // Log the list of all exercises (for debugging)
        Log.d("", allExercises.toString()) //JK
        // Check if the workout plan popup is clicked
        if (viewModel.popupClicked) { //JK
            WorkoutPlanPopup({  viewModel.popupClicked = false }, { viewModel.currentlyWorkingOut = true}) //JK
        }
        // Layout for workout plan listing
        Column { //JK
            // Workout plans title
            Text( //JK
                modifier = Modifier.fillMaxWidth(), //JK
                text = "Workout Plans", //JK
                fontSize = 36.sp, //JK
                textAlign = TextAlign.Center //JK
            ) //JK

            // Box layout for adding a new workout
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { //JK
                // Row for the add new workout button
                Row( //JK
                    modifier = Modifier.clickable {viewModel.currentlyWorkingOut = true }, //JK
                    horizontalArrangement = Arrangement.Center //JK
                ) { //JK
                    Text(text = "Add New Workout") //JK
                    Icon(Icons.Rounded.Add, contentDescription = "",) //JK
                } //JK

            } //JK

            // Grid layout for listing workouts
            LazyVerticalGrid( //JK
                columns = GridCells.Fixed(2), // Adjust the number of columns as needed //JK
                content = { //JK
                    items(5) { index -> //JK
                        WorkoutPlan({  viewModel.popupClicked = true }) //JK
                    } //JK
                } //JK
            ) //JK

        } //JK

    } //JK
}

@Composable
// Composable for displaying a single workout plan
fun WorkoutPlan(popupClicked: () -> Unit){
    // Elevated card for workout plan
    ElevatedCard( modifier = Modifier //JK
        .padding(10.dp) //JK
        .fillMaxWidth(0.4f), elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) { //JK
        // Row layout for workout plan content
        Row(){ //JK
            // Column for workout details
            Column( //JK
                modifier = Modifier //JK
                    .padding(10.dp) //JK
                    .weight(5f) //JK
            ) { //JK
                // Workout name
                Text(text = "Workout Name", fontSize = 20.sp) //JK
                // List of exercises
                for (index in 0..5){ //JK
                    Text(text = "Exercise ${index}") //JK
                } //JK

            } //JK

            // Icon for adding new content
            Icon( Icons.Rounded.Add, contentDescription ="", modifier = Modifier //JK
                .padding(10.dp) //JK
                .weight(2f) //JK
                .clickable { popupClicked() } ) //JK
        } //JK

        // Row for displaying last date of workout
        Row (modifier = Modifier //JK
            .padding(10.dp) //JK
            .fillMaxWidth(), horizontalArrangement = Arrangement.End){ //JK
            Text(text = "Last Date" ) //JK
        } //JK

    } //JK

}
