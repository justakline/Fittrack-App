package com.example.fittrack

//import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.*
import com.example.fittrack.WorkoutPlanScreen.WorkoutPlanScreen

@Composable
fun NavigationController(navController: NavHostController = rememberNavController()) {
    val exerciseList: ExerciseListViewModel = viewModel()
    LaunchedEffect(Unit) {
        exerciseList.setupExercises(initialExercises = createExercises())
    }


    NavHost(
        navController = navController,
        startDestination = Routes.ExerciseListScreen.route
    ) {
        //If we do nav.navigate(route), it will display whatever is in the corresponding composable
        composable(Routes.ExerciseListScreen.route) {
            ExerciseListScreen(exerciseList)
        }
        composable(Routes.ProgressScreen.route){
            ProgressScreen(exerciseList)
        }
        composable(Routes.WorkoutPlanScreen.route){
            WorkoutPlanScreen(allExercises = createExercises(),  exerciseList )
        }
    }
}