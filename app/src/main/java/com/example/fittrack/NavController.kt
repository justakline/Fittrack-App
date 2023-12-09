package com.example.fittrack

//import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.*

import androidx.navigation.compose.*
import com.example.fittrack.WorkoutPlanScreen.WorkoutPlanScreen

@Composable
fun NavigationController(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Routes.ExerciseListScreen.route
    ) {
        //If we do nav.navigate(route), it will display whatever is in the corresponding composable
        composable(Routes.ExerciseListScreen.route) {
            ExerciseListScreen(exercises = createExercises())
        }
        composable(Routes.ProgressScreen.route){
            ProgressScreen(exercises = createExercises())
        }
        composable(Routes.WorkoutPlanScreen.route){
            WorkoutPlanScreen(allExercises = createExercises())
        }
    }
}