package com.example.fittrack

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.BodyPart
import com.example.fittrack.models.ExerciseType
@Composable
// Screen for listing all exercises with a search and filter functionality
fun ExerciseListScreen(exercises: ExerciseListViewModel){
    var search by remember { mutableStateOf("Search") } // State to keep track of search text

    Column{ //JK
        AddNewWorkout() //JK
        Text(modifier = Modifier.fillMaxWidth(), text = "Exercises", fontSize = 36.sp, textAlign = TextAlign.Center) //JK
        SearchBar(searchValue = search, changeSearchValue = {i -> search = i}) //JK

        Categories() //JK
        Exercises(exercises) //JK

    }
}

@Composable
// Composable for adding a new workout
fun AddNewWorkout(){
    Box(modifier = Modifier.fillMaxWidth().padding( 8.dp), contentAlignment = Alignment.CenterStart){ //JK
        Row( modifier = Modifier.clickable {/* TODO  adding new workouts*/  },horizontalArrangement = Arrangement.Center) { //JK
            Text(text = "New", color = Color.Blue) //JK
            Icon(Icons.Rounded.Add, contentDescription ="",tint = Color.Blue ) //JK
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Search bar for filtering exercises
fun SearchBar(searchValue:String, changeSearchValue: (String) -> Unit){
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp), value = searchValue, onValueChange = {i -> changeSearchValue(i)}, singleLine = true) //JK
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Categories for filtering exercises by body part or exercise type
fun Categories (){//JK
    var byBodyPart by remember {mutableStateOf(false)} // State for body part filter
    var byExerciseType by remember {mutableStateOf(false)} // State for exercise type filter
    var textSize by remember{ mutableStateOf(14.sp) } // Text size for dropdown
    var dropDownSize by remember {mutableStateOf(200.dp) } // Size of the dropdown

    Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) { //JK
        Box(modifier = Modifier.weight(1f)) { //JK
            createDropDown(title = "By Body Part", expanded = byBodyPart, dropDownWidth = dropDownSize, textSize = textSize, //JK
                swapExpanded = {i-> byBodyPart = i}, BodyPart.values().map { i-> i.toString() }) //JK
        }
        Box (modifier = Modifier.weight(1f)) { //JK
            createDropDown(title = "By Category",expanded = byExerciseType, dropDownWidth = dropDownSize, textSize =textSize , //JK
                swapExpanded = {i-> byExerciseType = i}, ExerciseType.values().map{i->i.toString()} ) //JK
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Dropdown menu for selecting exercise categories
fun createDropDown(title: String, expanded: Boolean, dropDownWidth : Dp, textSize : TextUnit, swapExpanded: (Boolean) ->Unit, items: List<String>) {
    ExposedDropdownMenuBox(expanded =  expanded, modifier = Modifier.width(dropDownWidth), onExpandedChange = {swapExpanded(!expanded) } ) { //JK
        TextField(modifier = Modifier
            .clickable {}
            .menuAnchor()
            .height(55.dp)
            .padding(0.dp)
            .width(dropDownWidth),
            textStyle = TextStyle(fontSize = textSize, textAlign = TextAlign.Center),
            value = title,
            onValueChange ={}, readOnly = true,
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent), //JK
        )
        ExposedDropdownMenu(expanded =  expanded, onDismissRequest = {swapExpanded(false) }) { //JK
            items.forEach { part -> //JK
                DropdownMenuItem(text = { Text(text = part)}, onClick = {swapExpanded(false)}) //JK
            }
        }
    }
}

@Composable
// Displays exercises in a list format
fun Exercises(exerciseList: ExerciseListViewModel) {
    LazyColumn(modifier = Modifier.padding(10.dp, 4.dp),verticalArrangement = Arrangement.spacedBy(10.dp)){ //JK
        items(exerciseList.exercises.size){i -> //JK
            ExerciseCard(exerciseName = exerciseList.exercises[i].name, bodyPart = exerciseList.exercises[i].bodyPart.toString(), exerciseType = exerciseList.exercises[i].type.toString()) //JK
        }
    }
}

@Composable
// Card representation of each exercise
fun ExerciseCard(exerciseName:String, bodyPart: String, exerciseType:String){
    ElevatedCard (elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)){ //JK
        Column (modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        ){ //JK
            Text(text = exerciseName, fontSize = 20.sp) //JK
            Row { //JK
                Text(text = bodyPart) //JK
                Text(text = "\t\t\t\t") //JK
                Text(text = exerciseType) //JK
            }
        }
        Column (
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){ //JK

        } //JK
    } //JK
}