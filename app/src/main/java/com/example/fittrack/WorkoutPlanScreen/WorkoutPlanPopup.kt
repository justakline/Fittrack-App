package com.example.fittrack.WorkoutPlanScreen
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.fittrack.MainActivity
import com.example.fittrack.R
import com.example.fittrack.Notification.NotificationHelper


@Composable
fun WorkoutPlanPopup(popupClicked: ()->Unit, workoutClicked: () -> Unit) {//JK

    //Go on if they click Start Workout, go Back to WorkoutplanScreen if they click Go Back
    AlertDialog(onDismissRequest = { /* Empty because logic is down below*/ }, confirmButton = { /*Logic is below*/ },//JK

        dismissButton = {
            Button(modifier = Modifier.fillMaxWidth(),   onClick = { popupClicked()}, colors = ButtonDefaults.buttonColors(Color.Red, Color.White,Color.Gray, Color.Gray )) {//JK
                Text(text = "Go Back")//JK

            }
            Button( modifier = Modifier.fillMaxWidth(), onClick = { workoutClicked()}) {//JK
                Text(text = "Start Workout")//JK

            }

        }, )



}




