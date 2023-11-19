package com.example.fittrack

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.BodyPart
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseType

@Composable
fun ExerciseListScreen(exercises: MutableList<Exercise> ){
    var search by remember { mutableStateOf("Search") }


    Column{
        AddNewWorkout()
        Text(modifier = Modifier.fillMaxWidth(), text = "Exercises", fontSize = 36.sp, textAlign = TextAlign.Center)
        SearchBar(searchValue = search, changeSearchValue = {i -> search = i})

        Categories()
        Exercises(exercises)

    }



}

@Composable
fun AddNewWorkout(){
    Box(modifier = Modifier.fillMaxWidth().padding( 8.dp), contentAlignment = Alignment.CenterStart){
        Row( modifier = Modifier.clickable {/* TODO  adding new workouts*/  },horizontalArrangement = Arrangement.Center) {
            Text(text = "New", color = Color.Blue)
            Icon(Icons.Rounded.Add, contentDescription ="",tint = Color.Blue )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchValue:String, changeSearchValue: (String) -> Unit){
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp), value = searchValue, onValueChange = {i -> changeSearchValue(i)}, singleLine = true)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories (){
    //This will be used for if the user wants to see all the exercises of a certain bodypart or category
    var byBodyPart by remember {mutableStateOf(false)}
    var byExerciseType by remember {mutableStateOf(false)}
    var textSize by remember{ mutableStateOf(14.sp) }
    var dropDownSize by remember {mutableStateOf(200.dp) }


    Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        //Wrapping in a box Allows me to change the weight, ie how much, percentagewise it takes up
        Box(modifier = Modifier.weight(1f)) {
            createDropDown(title = "By Body Part", expanded = byBodyPart, dropDownWidth = dropDownSize, textSize = textSize,
                swapExpanded = {i-> byBodyPart = i}, BodyPart.values().map { i-> i.toString() })
        }
        Box (modifier = Modifier.weight(1f)) {
            createDropDown(title = "By Category",expanded = byExerciseType, dropDownWidth = dropDownSize, textSize =textSize ,
                swapExpanded = {i-> byExerciseType = i}, ExerciseType.values().map{i->i.toString()} )
        }



    }

}

/* The archietecture is
           MenuBox
               Textfield
               Dropdown
                   Item
                   Item

        */

//This is where the dropdown menu lives for the 2 categories that you can filter the list by
//The parameters hoist up the state and the changing some of the state as well as having conistent styling

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createDropDown(title: String, expanded: Boolean, dropDownWidth : Dp, textSize : TextUnit, swapExpanded: (Boolean) ->Unit, items: List<String>) {

    ExposedDropdownMenuBox(expanded =  expanded, modifier = Modifier.width(dropDownWidth), onExpandedChange = {swapExpanded(!expanded)
    } ) {
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

            colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),



        )
        ExposedDropdownMenu(expanded =  expanded, onDismissRequest = {swapExpanded(false) }) {
            //Create a new DropdownMenuItem for each Enum... Do this so If I change or add some more, it wont be hard coded
            items.forEach { part ->
                DropdownMenuItem(text = { Text(text = part)}, onClick = {swapExpanded(false)})

            }
        }
    }
}

@Composable
fun Exercises(exerciseList: MutableList<Exercise>) {

    //Use a lazy column for all the exercises so that we do not render every single one even when off screen
    LazyColumn(modifier = Modifier.padding(10.dp, 4.dp),verticalArrangement = Arrangement.spacedBy(10.dp)){
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
        Column (modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        ){
            Text(text = exerciseName, fontSize = 20.sp)

            Row {
                Text(text = bodyPart)
                Text(text = "\t\t\t\t")
                Text(text = exerciseType)
                //Text(text = "\t\t\t\t")

            }
        }
        Column (
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            Button(
//                onClick = { /*TODO*/ },
//                modifier = Modifier.fillMaxWidth(),
//            ) {
//                Spacer(modifier = Modifier.padding(bottom = 5.dp))
//                Text(text = "Add Workout")
//
//            }
        }


    }

}