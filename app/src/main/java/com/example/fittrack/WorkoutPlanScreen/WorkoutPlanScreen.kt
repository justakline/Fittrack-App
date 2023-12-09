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
import com.example.fittrack.models.Exercise

@Composable
fun WorkoutPlanScreen(allExercises: MutableList<Exercise>){
    val viewModel: WorkoutPlanViewModel = viewModel()
    //For choosing to go on and start a new workout
    var popupClicked by remember {mutableStateOf(false)}
    var currentlyWorkingOut by remember{ mutableStateOf(false) }

    //This will popup a new window of a current workout
    if(viewModel.currentlyWorkingOut){
        ActiveWorkoutScreen(allExercises = allExercises, {
            viewModel.popupClicked = false
            viewModel.currentlyWorkingOut = false})
    }else {
        Log.d("", allExercises.toString())
        if ( viewModel.popupClicked) {
            WorkoutPlanPopup({  viewModel.popupClicked = false }, { viewModel.currentlyWorkingOut = true})
        }
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Workout Plans",
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier.clickable {/* TODO  adding new workouts*/ },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Add New Workout")
                    Icon(Icons.Rounded.Add, contentDescription = "",)
                }

            }


            //List all of the workouts in a nx2 grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Adjust the number of columns as needed
                content = {
                    items(5) { index ->
                        WorkoutPlan({  viewModel.popupClicked = true })
                    }
                }
            )


        }

    }
}

@Composable
fun WorkoutPlan(popupClicked: () -> Unit){
    /* TODO Using data from database to fill these in with real data*/
    ElevatedCard( modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(0.4f), elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
        //Use a row so that I can have the + icon in the top right corner
        Row(){

            //Separates the Card into a title, and then the categories on the bottem
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(5f)
            ) {
                Text(text = "Workout Name", fontSize = 20.sp)
                for (index in 0..5){
                    Text(text = "Exercise ${index}")
                 }

            }


            Icon( Icons.Rounded.Add, contentDescription ="", modifier = Modifier
                .padding(10.dp)
                .weight(2f)
                .clickable { popupClicked() } )
        }


        Row (modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Text(text = "Last Date" )
        }


    }

}
