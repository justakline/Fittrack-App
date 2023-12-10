package com.example.fittrack


import android.util.Log
import androidx.annotation.Nullable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fittrack.models.Exercise

@Composable
// Popup for selecting exercises to add to a workout
fun ExerciseListPopup(allExercises: MutableList<Exercise>, exitPopup: ()->Unit,
                      addExerciseToWorkout:(Exercise) -> Unit) {
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) } // State to track the selected exercise

    // Dialog for exercise list
    Dialog(onDismissRequest = {exitPopup()} ){ //JK
        // Card layout for the popup content
        Card( //JK
            modifier = Modifier //JK
                .fillMaxWidth().padding(16.dp).fillMaxHeight(1/2f), //JK
            shape = RoundedCornerShape(16.dp), //JK
        ){ //JK
            // Column layout for the popup content
            Column { //JK
                // Row for add and cancel buttons
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { //JK
                    // Button to add the selected exercise
                    Button(onClick = {if (selectedExercise != null) addExerciseToWorkout(selectedExercise!!) }) { //JK
                        Text(text = "Add Exercise") //JK
                    } //JK
                    // Button to exit the popup
                    Button(onClick = { exitPopup()}) { //JK
                        Text(text = "Cancel") //JK
                    } //JK

                } //JK

                // List of exercises
                LazyColumn(){ //JK
                    items(allExercises){exercise -> //JK
                        // Each exercise is clickable
                        ClickableExercise(exercise, selectedExercise) { e -> selectedExercise = e } //JK
                    } //JK

                } //JK

            } //JK
        } //JK

    } //JK

}

@Composable
// Composable for a clickable exercise item
fun ClickableExercise(exercise: Exercise, selected:Exercise?, setSelected: (Exercise) -> Unit) {
    var clicked by remember { mutableStateOf(false) } // State to track if the exercise is clicked
    // Determine the background color based on selection state
    val backgroundColor = if ((selected == null && clicked) || (exercise.equals(selected) && clicked)) Color.LightGray else Color.Transparent //JK
    // Box layout for each exercise item
    Box(modifier = Modifier.clickable { //JK
        clicked = !clicked // Toggle clicked state
        setSelected(exercise) // Set the selected exercise
    }) { //JK
        // Row layout for exercise details
        Row(modifier = Modifier.background(backgroundColor).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { //JK
            Text(text = "${exercise.name}") //JK
            Text(text = "${exercise.type}") //JK
            Text(text = "${exercise.bodyPart}") //JK
        } //JK
    } //JK
}
