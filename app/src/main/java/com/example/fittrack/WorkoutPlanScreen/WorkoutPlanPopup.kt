package com.example.fittrack.WorkoutPlanScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun WorkoutPlanPopup(popupClicked: ()->Unit, workoutClicked: () -> Unit) {//JK

    //Go on if they click Start Workout, go Back to WorkoutplanScreen if they click Go Bacl
    AlertDialog(onDismissRequest = { /* Empty because logic is down below*/ }, confirmButton = { /*Logic is below*/ },//JK

        dismissButton = {
            Button(modifier = Modifier.fillMaxWidth(),   onClick = { popupClicked()}, colors = ButtonDefaults.buttonColors(Color.Red, Color.White,Color.Gray, Color.Gray )) {//JK
                Text(text = "Go Back")//JK

            }
            Button( modifier = Modifier.fillMaxWidth(), onClick = { workoutClicked() }) {//JK
                Text(text = "Start Workout")//JK

            }

        }, )



}