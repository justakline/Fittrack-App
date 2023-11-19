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
fun ExerciseListPopup(allExercises: MutableList<Exercise> , exitPopup: ()->Unit,
                     addExerciseToWorkout:(Exercise) -> Unit) {
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    Dialog(onDismissRequest = {exitPopup()} ){
        Card(
            modifier = Modifier
                .fillMaxWidth().padding(16.dp).fillMaxHeight(1/2f),
            shape = RoundedCornerShape(16.dp),
        ){
            Column {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {if (selectedExercise != null) addExerciseToWorkout(selectedExercise!!) }) {
                        Text(text = "Add Exercise")
                    }
                    Button(onClick = { exitPopup()}) {
                        Text(text = "Cancel")
                    }

                }



                LazyColumn(){
                    items(allExercises){exercise ->
                        ClickableExercise(exercise, selectedExercise) { e -> selectedExercise = e }
                    }

                }

            }
        }



    }



}

@Composable
fun ClickableExercise(exercise: Exercise, selected:Exercise?, setSelected: (Exercise) -> Unit) {
    var clicked by remember { mutableStateOf(false) }
    //Either There is none seleceted and we just clicked on it, or this exercise is the selected one
    val backgroundColor = if ((selected == null && clicked) || (exercise.equals(selected) && clicked)) Color.LightGray else Color.Transparent
    Box(modifier = Modifier.clickable {
        clicked = !clicked
        setSelected(exercise)}) {
        Row(modifier = Modifier.background(backgroundColor).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "${exercise.name}")
            Text(text = "${exercise.type}")
            Text(text = "${exercise.bodyPart}")
        }
    }
}
