package com.example.fittrack

//package com.example.fittrack

import WorkoutPlanPopup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.Exercise
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun ActiveWorkoutScreen(exercises: MutableList<Exercise>, returnToWorkouts: ()->Unit){

    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Icon(Icons.Rounded.Check, contentDescription = "",   modifier = Modifier
                .weight(1f)
                .clickable { returnToWorkouts()},)
            Text(
                modifier = Modifier.weight(5f),
                text = "Default Workout Name",
                fontSize = 22.sp,
                textAlign = TextAlign.Left
            )
            Icon(Icons.Rounded.Edit, contentDescription = "",   modifier = Modifier
                .weight(1f)
                .clickable { },)
        }
        //Allow For things in the box to be left oriented while everything else be centered
        Box(modifier = Modifier.fillMaxWidth()){
            //Exercises
            LazyColumn(){
                /* TODO Where the exercises will be */
                item { Exercise("Lifts") }
            }
        }

        /*TODO Need to style better*/
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add Exercise")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Cancel Workout")
        }

    }


}

@Composable
fun Exercise(exerciseName: String){
    Column {
        Text(text = "${exerciseName}")
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Set")
            Text(text = "LBS")
            Text(text = "Reps")
        }
        ExerciseRow(set =0 , lbs =25 , reps = 0, true, false)

        ExerciseRow(set =1 , lbs =25 , reps = 0, true, true)

        ExerciseRow(set =2 , lbs =25 , reps = 0, true, false)
    }


}

@Composable
fun ExerciseRow(set: Int, lbs: Int, reps: Int, lbsDefault: Boolean, repsDefault: Boolean){
    Row(horizontalArrangement = Arrangement.SpaceBetween){

        Card(shape = RoundedCornerShape(10.dp)) {
            Text(text = "${set}", color = Color.Gray)
        }
        Card(shape = RoundedCornerShape(10.dp)) {
            Text(text = if (lbsDefault) "${0}" else "${set}", color = if (lbsDefault) Color.LightGray else Color.Gray)
        }
        Card(shape = RoundedCornerShape(10.dp)) {
            Text(text = if (repsDefault) "${0}" else "${reps}", color = if (repsDefault) Color.LightGray else Color.Gray)
        }



    }
}
