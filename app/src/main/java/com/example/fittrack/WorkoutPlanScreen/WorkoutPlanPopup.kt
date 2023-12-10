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
fun WorkoutPlanPopup(popupClicked: ()->Unit, workoutClicked: () -> Unit) {

    AlertDialog(onDismissRequest = { /* Empty because logic is down below*/ }, confirmButton = { /*Logic is below*/ },

        dismissButton = {
            Button(modifier = Modifier.fillMaxWidth(),   onClick = { popupClicked()}, colors = ButtonDefaults.buttonColors(Color.Red, Color.White,Color.Gray, Color.Gray )) {
                Text(text = "Go Back")

            }
            Button( modifier = Modifier.fillMaxWidth(), onClick = { workoutClicked() }) {
                Text(text = "Start Workout")

            }

        }, )



}