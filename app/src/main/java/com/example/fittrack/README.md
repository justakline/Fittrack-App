# Fittrack Project Readme
Created by _Justin Kline_, _Jacob Jedlowski_, _William Slade_
## Summary
Fittrack acts as your digital workout companion, designed to keep an eye on your fitness and keep track of it. It lets you  new exercises, whip up workout plans, and see into  past exercises.

## Classes and Functions
### Enumerations
- _BodyPart_: Represents different body parts (LEGS, ARMS, CHEST, etc.).
- _ExerciseType_: These are th Types of exercises ( DURATION, DUMBELL, BARBELL...).

### Data Classes
- _Exercise_:  This is where we keep all the info about an exercise, like what it's called, what type it is (like dumbbells or cardio), which part of the body it works on, and all the details of how you did each time you did this exercise.
- _ExerciseDataPoint_:  Think of this as a record of one round of an exercise. It notes how much weight you lifted (if it's that kind of exercise), how many sets and reps you did in that round.

### Composable Functions
- _ActiveWorkoutScreen_: : This screen shows your current workout and lets you add or change the exercises you're doing.
- _Exercise_: You can see each exercise here, along with options to tweak how many sets you're doing and other details.
- _ExerciseRow_: Shows you the each set in your exercise routine
- _NewWorkoutScreen_: Screen for creating a new workout plan. 
- _WorkoutPlanPopup_: This pops up to double-check that you're ready to add a new workout
- _WorkoutPlanScreen_: Displays available workout plans and you can to create new ones
- _ProgressScreen_: Shows the last 5 times you did each exercise
-  _ExerciseProgressItem_: An exercise and its last 5 history
- _ExerciseListPopup_: Popup for selecting exercises to add to a workout.
- _ClickableExercise_: It's an exercise item you can click on when choosing what to add to your workout.
- _ExerciseListScreen_: This screen's where all the exercises are listed. You can search and filter...
- _AddNewWorkout_, _SearchBar_, _Categories_: Components for adding new workouts and searching. 
- _createDropDown_: A dropdown component for filtering exercises. 
- _Exercises_, _ExerciseCard_: They show off the exercises in a neat list or on individual cards for easy viewing.

### ViewModel Classes
- _WorkoutPlanViewModel_: Keeps hold of the state for recomp
- _ExerciseListViewModel_: It's in charge of keeping a list of all your exercises and passes into each screen

### Main Activity and Navigation
- _MainActivity_: The main activity initializing the app theme and content. 
- _MainScreen_, _BottomBar_, _NavigationController_, _Routes_: Navigation parts

## Third-Party Libraries/Resources
- _Jetpack Compose_: DUH... UI and such
- _Material3_: For Material Design components. 
- _ViewModel_: Managing state on recomp
- _StackOverflow_: For debugging and explanation -- https://developer.android.com/jetpack/compose/
- _Android Developer_: For general Kotlin and Jetpack Compose Questions: https://developer.android.com/jetpack/compose/
- _Kotlin Docs_: Specific Kotline questions: https://kotlinlang.org/docs/home.html

## Installation and Setup
Clone the Repo. Ensure you have the latest version of Android Studio, Reload From Disk, Sync The Gradle, Start A New device, and Run
Repo URL: https://bitbucket.org/justinkline/fittrack/
Git URL for cloning: https://bitbucket.org/justinkline/fittrack.git


## Usage Instructions
1. _Starting the App_: Just launche the app and use the bottom navigation bar for moving around. 
2. _Creating Workouts_: Head over to the **Workout Plan** area and hit that **New Workout** button to starts crafting your routine. 
3. _Tracking Exercises_: When you adds exercises to your workout, you can keep tab on all the sets, reps, and weights you lifting. 
4. _Viewing Progress_: To see of how far you've come, the **Progress** section lay out your exercise history.

**README** was created by Justin Kline