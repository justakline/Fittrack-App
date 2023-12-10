package com.example.fittrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseDataPoint
@Composable
// Screen to display the user's progress in terms of exercise attempts
fun ProgressScreen(viewModel: ExerciseListViewModel) {
    // Column layout for the screen
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) { //JK
        Text(text = "Your last 5 Attempts" ) //JK

        // Grid to display a list of exercises
        LazyVerticalGrid( //JK
            columns = GridCells.Fixed(2), //JK
            contentPadding = PaddingValues(all = 8.dp), //JK
            horizontalArrangement = Arrangement.spacedBy(8.dp),//JK
            verticalArrangement = Arrangement.spacedBy(8.dp)//JK
        ) { //JK
            items(viewModel.exercises, key = { it.name }) { exercise -> // Iterate over exercises
                ExerciseProgressItem(exercise = exercise, viewModel = viewModel) //JK
            } //JK
        } //JK

        // Future implementation for adding a new exercise
    } //JK
}

@Composable
// Composable to display progress details for each exercise
fun ExerciseProgressItem(exercise: Exercise, viewModel: ExerciseListViewModel) {
    val lastFiveSets = viewModel.getLastFiveSets(exercise) // Get the last five sets of the exercise //JK
    // Card layout for each exercise progress
    Card( //JK
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) { //JK
        // Row layout for exercise details and progress
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { //JK
            Text(exercise.name, modifier = Modifier.weight(1f))  //JK
            Column(modifier = Modifier.fillMaxWidth()) { //JK
                Text(exercise.name, modifier = Modifier.padding(8.dp)) //JK
                // Iterate over the last five sets
                lastFiveSets.forEach { setDataPoint -> //JK
                    // Details for each set
                    Text("Set: ${setDataPoint.set}, Reps: ${setDataPoint.rep}, Weight: ${setDataPoint.lbs}") //JK
                } //JK
                // Check if there is no data yet
                if (lastFiveSets.isEmpty()) { //JK
                    Text("No data yet") // Display message if no data //JK
                } //JK
            } //JK
        } //JK
    } //JK
}
