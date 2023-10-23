package com.example.fittrack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.Exercise

@Composable
fun WorkoutPlanScreen(exercises: MutableList<Exercise>){
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Workout Plan",
            fontSize = 36.sp,
            textAlign = TextAlign.Center
            )
        Row {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = "Workouts:",
                fontSize = 24.sp,
                textAlign = TextAlign.Left)
        }
        WorkoutPlan()
    }


}

@Composable
fun WorkoutPlan(){
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Workout")
            Row {
                Text(text = "Sets: ")
            }
            Row {
                Text(text = "Reps: ")
            }

        }
    }
}