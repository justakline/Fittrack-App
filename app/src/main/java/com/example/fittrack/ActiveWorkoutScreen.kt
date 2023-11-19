package com.example.fittrack

//package com.example.fittrack

import WorkoutPlanPopup
import androidx.annotation.Nullable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseData
import com.example.fittrack.models.ExerciseDataPoint
import com.example.fittrack.models.WorkoutData
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun ActiveWorkoutScreen(allExercises: MutableList<Exercise>, returnToWorkouts: ()->Unit){
    var currentExercises by remember { mutableStateOf(mutableListOf<Exercise>()) }
    var addExerciseClicked by remember { mutableStateOf(false) }
    var workoutData by remember {
        mutableStateOf(WorkoutData(mutableListOf<ExerciseData>(), Date()))
    }

    if(addExerciseClicked){
        ExerciseListPopup(allExercises = allExercises, exitPopup = { addExerciseClicked = false },
             addExerciseToWorkout = {exercise ->  currentExercises = currentExercises.toMutableList().apply { add(exercise) }
                                                    addExerciseClicked = false})
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
                items(currentExercises){exercise->
                    Exercise(exercise, workoutData)
                }
                /* TODO Where the exercises will be */

            }
        }

        /*TODO Need to style better*/
        Button(onClick = { addExerciseClicked = true }) {
            Text(text = "Add Exercise")
        }
        Button(onClick = { returnToWorkouts() }) {
            Text(text = "Cancel Workout")
        }

    }


}

@Composable
fun Exercise(exercise: Exercise, workoutData: WorkoutData){
    var sets by remember { mutableStateOf(0)}
    var dataPoints by remember{ mutableStateOf(mutableListOf<ExerciseDataPoint>() ) }
    var exerciseData by remember {
        mutableStateOf( ExerciseData(exercise,dataPoints))
    }
    Column {
        Text(text = "${exercise.name}")
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(modifier = Modifier.weight(3f),text = "Set")
            Text(modifier = Modifier.weight(3f),text = "LBS")
            Text(modifier = Modifier.weight(3f),text = "Reps")
            Icon(Icons.Rounded.Delete, contentDescription = "",modifier = Modifier.weight(1f),)
        }
        LazyColumn(modifier = Modifier.height((60+60*dataPoints.size).dp)){

//            dataPoints.forEach { dataPoint->
//                ExerciseRow(setNumber = dataPoint.set)
//            }
            items(dataPoints){ i ->
                ExerciseRow(setNumber = i.set)
            }
            item{
                Button( modifier = Modifier.fillMaxWidth(), onClick = {
                    dataPoints = dataPoints.toMutableList().apply { add(ExerciseDataPoint(exercise, 0,0,0))} } )

                {
                    Text(text = "Add Set")
                }
            }
        }



//
//        ExerciseRow(set =1 , lbs =25 , reps = 0, true, true)
//
//        ExerciseRow(set =2 , lbs =25 , reps = 0, true, false)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseRow(setNumber: Int){
    var lbs by remember { mutableStateOf<Int?>(null)}
    var reps by remember { mutableStateOf<Int?>(null)}
    val default = 0
    //Only a number keyboard is allowed
    val keyBoard = KeyboardOptions(keyboardType = KeyboardType.Number)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        //Read only, keeping consistent ui
        Card(modifier = Modifier.weight(3f), shape = RoundedCornerShape(10.dp)) {
            TextField("${setNumber}", onValueChange = {}, readOnly = true)
        }
        //Changing values by the user, doing s=="" for error checking
        Card(modifier = Modifier.weight(3f),shape = RoundedCornerShape(10.dp)) {
            TextField("${if (lbs ==null) "" else lbs }", onValueChange = {s-> if(s.toIntOrNull()!= null) lbs = s.toInt() else lbs= null}, placeholder = {Text("0")}, keyboardOptions = keyBoard)
        }
        Card(modifier = Modifier.weight(3f),shape = RoundedCornerShape(10.dp)) {
            TextField("${if (reps ==null) "" else reps }", onValueChange = {s-> if(s.toIntOrNull()!= null) reps = s.toInt() else reps= null}, placeholder = {Text("0")}, keyboardOptions = keyBoard)
        }
        Icon(Icons.Rounded.Delete, contentDescription = "", modifier = Modifier.weight(1f))




    }
}
