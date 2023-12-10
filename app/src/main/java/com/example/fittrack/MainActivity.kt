package com.example.fittrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fittrack.models.BodyPart
import com.example.fittrack.models.Exercise
import com.example.fittrack.models.ExerciseDataPoint
import com.example.fittrack.models.ExerciseType
import com.example.fittrack.ui.theme.FittrackTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val exercises = createExercises()
        setContent {
            FittrackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FittrackTheme {

    }
}

//Created all of these exercises as dummy information
fun createExercises(): MutableList<Exercise> {
    return mutableListOf<Exercise>().apply {
        // Legs
        add(Exercise("Squats", ExerciseType.BARBELL, BodyPart.LEGS, mutableListOf ()))
        add(Exercise("Lunges", ExerciseType.BARBELL, BodyPart.LEGS, mutableListOf ()))
        add(Exercise("Leg Press", ExerciseType.MACHINE, BodyPart.LEGS, mutableListOf ()))
        add(Exercise("Calf Raises", ExerciseType.MACHINE, BodyPart.LEGS, mutableListOf ()))
        add(Exercise("Jump Squats", ExerciseType.REPS, BodyPart.LEGS, mutableListOf ()))

        // Arms
        add(Exercise("Bicep Curls", ExerciseType.DUMBELL, BodyPart.ARMS, mutableListOf ()))
        add(Exercise("Tricep Dips", ExerciseType.MACHINE, BodyPart.ARMS, mutableListOf ()))
        add(Exercise("Hammer Curls", ExerciseType.DUMBELL, BodyPart.ARMS, mutableListOf ()))

        // Chest
        add(Exercise("Bench Press", ExerciseType.BARBELL, BodyPart.CHEST, mutableListOf ()))
        add(Exercise("Chest Fly", ExerciseType.DUMBELL, BodyPart.CHEST, mutableListOf ()))
        add(Exercise("Push-Ups", ExerciseType.REPS, BodyPart.CHEST, mutableListOf ()))
        add(Exercise("Pullovers", ExerciseType.DUMBELL, BodyPart.CHEST, mutableListOf ()))
        add(Exercise("Dumbbell Press", ExerciseType.DUMBELL, BodyPart.CHEST, mutableListOf ()))

        // Back
        add(Exercise("Pull-Ups", ExerciseType.REPS, BodyPart.BACK, mutableListOf ()))
        add(Exercise("Bent Over Rows", ExerciseType.BARBELL, BodyPart.BACK, mutableListOf ()))
        add(Exercise("Deadlifts", ExerciseType.BARBELL, BodyPart.BACK, mutableListOf ()))
        add(Exercise("Face Pulls", ExerciseType.MACHINE, BodyPart.BACK, mutableListOf ()))
        add(Exercise("Lat Pull Downs", ExerciseType.MACHINE, BodyPart.BACK, mutableListOf ()))

        // Abs
        add(Exercise("Sit-Ups", ExerciseType.REPS, BodyPart.CORE, mutableListOf ()))
        add(Exercise("Plank", ExerciseType.DURATION, BodyPart.CORE, mutableListOf ()))
        add(Exercise("Russian Twists", ExerciseType.REPS, BodyPart.CORE, mutableListOf ()))
        add(Exercise("Leg Raises", ExerciseType.REPS, BodyPart.CORE, mutableListOf ()))
        add(Exercise("Mountain Climbers", ExerciseType.DURATION, BodyPart.CORE, mutableListOf ()))

        // Shoulders
        add(Exercise("Shoulder Press", ExerciseType.BARBELL, BodyPart.SHOULDERS, mutableListOf ()))
        add(Exercise("Lateral Raises", ExerciseType.DUMBELL, BodyPart.SHOULDERS, mutableListOf ()))
        add(Exercise("Front Raises", ExerciseType.DURATION, BodyPart.SHOULDERS, mutableListOf ()))
        add(Exercise("Shrugs", ExerciseType.DUMBELL, BodyPart.SHOULDERS, mutableListOf ()))
        add(Exercise("Reverse Fly", ExerciseType.DUMBELL, BodyPart.SHOULDERS, mutableListOf ()))

        // Full Body
        add(Exercise("Burpees", ExerciseType.DURATION, BodyPart.FULL_BODY, mutableListOf ()))
        add(Exercise("Jumping Jacks", ExerciseType.REPS, BodyPart.FULL_BODY, mutableListOf ()))
        add(Exercise("Tuck Jumps", ExerciseType.REPS, BodyPart.FULL_BODY, mutableListOf ()))
        add(Exercise("Bear Crawl", ExerciseType.DURATION, BodyPart.FULL_BODY, mutableListOf ()))
        add(Exercise("High Knees", ExerciseType.DURATION, BodyPart.FULL_BODY, mutableListOf ()))
    }
}