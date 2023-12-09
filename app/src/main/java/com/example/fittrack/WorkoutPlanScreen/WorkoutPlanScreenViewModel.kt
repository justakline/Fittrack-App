import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class WorkoutPlanViewModel : ViewModel() {
    var popupClicked by mutableStateOf(false)
    var currentlyWorkingOut by mutableStateOf(false)

}