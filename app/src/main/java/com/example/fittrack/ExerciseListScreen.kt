package com.example.fittrack

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.BodyPart
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseType

@Composable
fun ExerciseListScreen(exercises: MutableList<Exercise> ){



Column{
    Text(text = "Exercises")
    SearchBar()
    Categories()
    Exercises(exercises)

}



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(){
    TextField(value = "Search", onValueChange = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories (){
    //This will be used for if the user wants to see all the exercises of a certain bodypart or category
    var byBodyPart by remember {mutableStateOf(false)}
    var byExerciseType by remember {mutableStateOf(false)}
    Row {
        /* The archietecture is
            MenuBox
                Textfield
                Dropdown
                    Item
                    Item

         */
        ExposedDropdownMenuBox(expanded =  byBodyPart, onExpandedChange = {byBodyPart = !byBodyPart
        } ) {
            TextField(modifier = Modifier.clickable {}.menuAnchor(), value = "By BodyPart", onValueChange ={}, readOnly = true )
            ExposedDropdownMenu(expanded =  byBodyPart, onDismissRequest = { byBodyPart = false }) {
                //Create a new DropdownMenuItem for each BodyPart Enum... Do this so If I change or add some more, it wont be hard coded
                BodyPart.values().forEach { part ->
                    DropdownMenuItem(text = { Text(text = part.toString())}, onClick = { byBodyPart = false })

                }
            }
        }
        ExposedDropdownMenuBox(expanded =  byExerciseType, onExpandedChange = {byExerciseType = !byExerciseType
        } ) {
            TextField(modifier = Modifier.clickable {}.menuAnchor(), value = "By Category", onValueChange ={}, readOnly = true )
            ExposedDropdownMenu(expanded =  byExerciseType, onDismissRequest = { byExerciseType = false }) {
                //Create a new DropdownMenuItem for each BodyPart Enum... Do this so If I change or add some more, it wont be hard coded
                ExerciseType.values().forEach { type ->
                    DropdownMenuItem(text = { Text(text = type.toString())}, onClick = { byExerciseType = false })

                }
            }
        }
    }

}

@Composable
fun Exercises(exerciseList: MutableList<Exercise>) {

    //Use a lazy column for all the exercises so that we do not render every single one even when off screen
    LazyColumn(modifier = Modifier.padding(0.dp, 3.dp),verticalArrangement = Arrangement.spacedBy(10.dp)){
        items(exerciseList.size){i ->
            ExerciseCard(exerciseName = exerciseList[i].name, bodyPart = exerciseList[i].bodyPart.toString(), exerciseType = exerciseList[i].type.toString())
        }
    }
}
@Composable
fun ExerciseCard(exerciseName:String, bodyPart: String, exerciseType:String){
    //Using the documentation I found a elevated cards composable, it makes lists look niice
    //Card elevation gives how much shadow to make it look elevated
    ElevatedCard (elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)){

        //Separates the Card into a title, and then the categories on the bottem
        Column (modifier = Modifier.padding(10.dp).fillMaxWidth()){
            Text(text = exerciseName, fontSize = 20.sp)
            Row {
                Text(text = bodyPart)

                Text(text = "\t\t\t\t")
                Text(text = exerciseType)
            }
        }
    }

}