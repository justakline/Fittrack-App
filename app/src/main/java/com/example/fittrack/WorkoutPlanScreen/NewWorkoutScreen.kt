package com.example.fittrack.WorkoutPlanScreen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fittrack.ExerciseListPopup
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseData
import com.example.fittrack.models.WorkoutData
import java.util.Date

@Composable
fun NewWorkoutScreen (allExercises: MutableList<Exercise>, returnToWorkouts: ()->Unit, viewModel: WorkoutPlanViewModel){


    if(viewModel.addExerciseClicked){
        ExerciseListPopup(allExercises = allExercises, exitPopup = { viewModel.addExerciseClicked = false },
            addExerciseToWorkout = {exercise ->  viewModel.currentExercises = viewModel.currentExercises.toMutableList().apply { add(exercise) }
                viewModel.addExerciseClicked = false})
    }
    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Icon(Icons.Rounded.Check, contentDescription = "",   modifier = Modifier
                .weight(1f)
                .clickable { returnToWorkouts() },)
            Text(
                modifier = Modifier.weight(5f),
                text = "Default Workout Name",
                fontSize = 22.sp,
                textAlign = TextAlign.Left
            )

        }
        //Allow For things in the box to be left oriented while everything else be centered
        Box(modifier = Modifier.fillMaxWidth()){
            //Exercises
            LazyColumn(){
                items(viewModel.currentExercises){exercise->

                    Exercise(exercise, viewModel)
                }
                /* TODO Where the exercises will be */

            }
        }

        /*TODO Need to style better*/
        Button(onClick = { viewModel.addExerciseClicked = true }) {
            Text(text = "Add Exercise")
        }
        Button(onClick = { returnToWorkouts() }) {
            Text(text = "Cancel Workout")
        }

    }


}


//
//
//    var workoutData by remember {
//        mutableStateOf(WorkoutData(mutableListOf<ExerciseData>(), Date()))
//    }
//
//    if(viewModel.addWorkoutClicked){
//        ExerciseListPopup(allExercises = allExercises, exitPopup = { viewModel.addWorkoutClicked = false },
//            addExerciseToWorkout = {exercise ->  viewModel.currentExercises = viewModel.currentExercises.toMutableList().apply { add(exercise) }
//                viewModel.addWorkoutClicked = false})
//    }
//
//    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//        Row (horizontalArrangement = Arrangement.SpaceBetween){
//            Icon(
//                Icons.Rounded.Check, contentDescription = "",   modifier = Modifier
//                .weight(1f)
//                .clickable { returnToWorkouts() },)
//            Text(
//                modifier = Modifier.weight(5f),
//                text = "Default Workout Name",
//                fontSize = 22.sp,
//                textAlign = TextAlign.Left
//            )
//
//        }
//        //Allow For things in the box to be left oriented while everything else be centered
//        Box(modifier = Modifier.fillMaxWidth()){
//            //Exercises
//            LazyColumn(){
//                items(viewModel.currentExercises){exercise->
//
//                    Exercise(exercise, viewModel)
//                }
//                /* TODO Where the exercises will be */
//
//            }
//        }
//
//        /*TODO Need to style better*/
//        Button(onClick = { viewModel.addWorkoutClicked = true }) {
//            Text(text = "Add Exercise")
//        }
//        Button(onClick = { returnToWorkouts() }) {
//            Text(text = "Cancel Workout")
//        }
//
//    }
//
//
//}
//
//@Composable
//fun Exercise(exercise: Exercise, viewModel: NewWorkoutScreenViewModel){
//    var sets by remember { mutableStateOf(0) }
//
//    Column {
//        Text(text = "${exercise.name}")
//        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
//            Text(modifier = Modifier.weight(3f),text = "Set")
//            Text(modifier = Modifier.weight(3f),text = "LBS")
//            Text(modifier = Modifier.weight(3f),text = "Reps")
//            Icon(Icons.Rounded.Delete, contentDescription = "",modifier = Modifier.weight(1f).clickable {
//                viewModel.removeExercise(exercise)
//
//            })
//        }
//        LazyColumn(modifier = Modifier.height((60 + 60 * exercise.exerciseDataPoints.size).dp)) {
//            items(exercise.exerciseDataPoints) { dataPoint ->
//                ExerciseRow(setNumber = dataPoint.set, onDelete = {
//                    // Update the exerciseDataPoints for this specific exercise
//                    viewModel.removeSetFromExercise(dataPoint, exercise)
//                }, viewModel)
//            }
//            item {
//                Button(modifier = Modifier.fillMaxWidth(), onClick = {
//                    viewModel.addSetToExercise(exercise)
//
//                }) {
//                    Text(text = "Add Set")
//                }
//            }
//        }
//
//
//
////
////        ExerciseRow(set =1 , lbs =25 , reps = 0, true, true)
////
////        ExerciseRow(set =2 , lbs =25 , reps = 0, true, false)
//    }
//
//
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ExerciseRow(setNumber: Int, onDelete: () -> Unit, viewModel: NewWorkoutScreenViewModel){
//    var lbs by rememberSaveable { mutableStateOf(0) }
//    var reps by rememberSaveable { mutableStateOf(0) }
//    val default = 0
//    //Only a number keyboard is allowed
//    val keyBoard = KeyboardOptions(keyboardType = KeyboardType.Number)
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
//        //Read only, keeping consistent ui
//        Card(modifier = Modifier.weight(3f), shape = RoundedCornerShape(10.dp)) {
//            TextField("${setNumber}", onValueChange = {}, readOnly = true)
//        }
//        //Changing values by the user, doing s=="" for error checking
//        Card(modifier = Modifier.weight(3f),shape = RoundedCornerShape(10.dp)) {
//            TextField(lbs.toString(), onValueChange = {s->  if(s.toIntOrNull() != null ) lbs = s.toInt()}, placeholder = { Text("0") }, keyboardOptions = keyBoard)
//        }
//        Card(modifier = Modifier.weight(3f),shape = RoundedCornerShape(10.dp)) {
//            TextField(reps.toString(), onValueChange = {s->  if(s.toIntOrNull() != null ) reps = s.toInt()}, placeholder = { Text("0") }, keyboardOptions = keyBoard)
//        }
//        Icon(
//            Icons.Rounded.Delete, contentDescription = "", modifier = Modifier
//            .weight(1f)
//            .clickable { onDelete() } )
//
//
//
//
//    }
//}
