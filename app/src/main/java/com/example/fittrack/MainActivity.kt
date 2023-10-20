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
        add(Exercise("Squats", ExerciseType.BARBELL, BodyPart.LEGS))
        add(Exercise("Lunges", ExerciseType.BARBELL, BodyPart.LEGS))
        add(Exercise("Leg Press", ExerciseType.MACHINE, BodyPart.LEGS))
        add(Exercise("Calf Raises", ExerciseType.MACHINE, BodyPart.LEGS))
        add(Exercise("Jump Squats", ExerciseType.REPS, BodyPart.LEGS))

        // Arms
        add(Exercise("Bicep Curls", ExerciseType.DUMBELL, BodyPart.ARMS))
        add(Exercise("Tricep Dips", ExerciseType.MACHINE, BodyPart.ARMS))
        add(Exercise("Hammer Curls", ExerciseType.DUMBELL, BodyPart.ARMS))

        // Chest
        add(Exercise("Bench Press", ExerciseType.BARBELL, BodyPart.CHEST))
        add(Exercise("Chest Fly", ExerciseType.DUMBELL, BodyPart.CHEST))
        add(Exercise("Push-Ups", ExerciseType.REPS, BodyPart.CHEST))
        add(Exercise("Pullovers", ExerciseType.DUMBELL, BodyPart.CHEST))
        add(Exercise("Dumbbell Press", ExerciseType.DUMBELL, BodyPart.CHEST))

        // Back
        add(Exercise("Pull-Ups", ExerciseType.REPS, BodyPart.BACK))
        add(Exercise("Bent Over Rows", ExerciseType.BARBELL, BodyPart.BACK))
        add(Exercise("Deadlifts", ExerciseType.BARBELL, BodyPart.BACK))
        add(Exercise("Face Pulls", ExerciseType.MACHINE, BodyPart.BACK))
        add(Exercise("Lat Pull Downs", ExerciseType.MACHINE, BodyPart.BACK))

        // Abs
        add(Exercise("Sit-Ups", ExerciseType.REPS, BodyPart.CORE))
        add(Exercise("Plank", ExerciseType.DURATION, BodyPart.CORE))
        add(Exercise("Russian Twists", ExerciseType.REPS, BodyPart.CORE))
        add(Exercise("Leg Raises", ExerciseType.REPS, BodyPart.CORE))
        add(Exercise("Mountain Climbers", ExerciseType.DURATION, BodyPart.CORE))

        // Shoulders
        add(Exercise("Shoulder Press", ExerciseType.BARBELL, BodyPart.SHOULDERS))
        add(Exercise("Lateral Raises", ExerciseType.DUMBELL, BodyPart.SHOULDERS))
        add(Exercise("Front Raises", ExerciseType.DURATION, BodyPart.SHOULDERS))
        add(Exercise("Shrugs", ExerciseType.DUMBELL, BodyPart.SHOULDERS))
        add(Exercise("Reverse Fly", ExerciseType.DUMBELL, BodyPart.SHOULDERS))

        // Full Body
        add(Exercise("Burpees", ExerciseType.DURATION, BodyPart.FULL_BODY))
        add(Exercise("Jumping Jacks", ExerciseType.REPS, BodyPart.FULL_BODY))
        add(Exercise("Tuck Jumps", ExerciseType.REPS, BodyPart.FULL_BODY))
        add(Exercise("Bear Crawl", ExerciseType.DURATION, BodyPart.FULL_BODY))
        add(Exercise("High Knees", ExerciseType.DURATION, BodyPart.FULL_BODY))
    }
}