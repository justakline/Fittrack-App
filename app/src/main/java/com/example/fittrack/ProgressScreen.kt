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
fun ProgressScreen(viewModel: ExerciseListViewModel) {


    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Your last 5 Attempts" )

        // Display list of exercises
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(all = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.exercises, key = { it.name }) { exercise ->
                ExerciseProgressItem(exercise = exercise, viewModel = viewModel)
            }

        }

        // Button to add a new exercise (Implement the logic to choose an exercise)

    }
}


@Composable
fun ExerciseProgressItem(exercise: Exercise, viewModel: ExerciseListViewModel) {
    val lastFiveSets = viewModel.getLastFiveSets(exercise)
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(exercise.name, modifier = Modifier.weight(1f))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(exercise.name, modifier = Modifier.padding(8.dp))
//                Text(text = "${viewModel.getExercise(exercise).exerciseDataPoints}")





                lastFiveSets.forEach { setDataPoint ->
                    Text("Set: ${setDataPoint.set}, Reps: ${setDataPoint.rep}, Weight: ${setDataPoint.lbs}")
                }
                if (lastFiveSets.isEmpty()) {
                    Text("No data yet")
                }

            }


        }
    }
}