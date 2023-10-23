package com.example.fittrack

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    //Used for navigating between Screens
    val nav = rememberNavController()
    Scaffold(

        //Bottom bar will have the links to the other screens
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
        NavigationController(navController = nav)
    }
}


@Composable
private fun BottomBar(
    nav: NavHostController
) {

    var current by remember { mutableStateOf(Routes.ExerciseListScreen.route) }
    NavigationBar() {

        //Fill in with your route
        NavigationBarItem(
            selected =  nav.currentBackStackEntry?.destination?.route == Routes.ProgressScreen.route,
            onClick = {
                nav.navigate(Routes.ProgressScreen.route){
                    current = Routes.ProgressScreen.route;
                }
            },
            icon = {
                Icon(Icons.Default.Build, "")
            },
            label = {
                Text("Progress")
            }
        )
        NavigationBarItem(
                selected =  nav.currentBackStackEntry?.destination?.route == Routes.WorkoutPlanScreen.route,
        onClick = {
            nav.navigate(Routes.WorkoutPlanScreen.route){
                current = Routes.WorkoutPlanScreen.route;
            }
        },
        icon = {
            Icon(Icons.Default.PlayArrow, "")
        },
        label = {
            Text("Workout Plans")
        }
        )

        //When clicked, it will navigate to the corresponding screen... lights up blue if it is the current
        NavigationBarItem(
            //Checks if the top screen is the current one
            selected =  nav.currentBackStackEntry?.destination?.route == Routes.ExerciseListScreen.route,
            onClick = {
                //Only reload the screen if we are not currently at same screen
                if(current != Routes.ExerciseListScreen.route) {
                    nav.navigate(Routes.ExerciseListScreen.route) {
                        current = Routes.ExerciseListScreen.route;
                    }
                }
            },
            icon = {
                Icon(Icons.Default.CheckCircle, "", modifier = Modifier, tint = if(current == Routes.ExerciseListScreen.route) Color.Blue else Color.Gray)
            },
            label = {
                Text("Exercises")
            }
        )




    }
}