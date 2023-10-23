package com.example.fittrack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.fittrack.models.Exercise

@Composable
fun ProgressScreen(exercises: MutableList<Exercise>){
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Progress",
            fontSize = 36.sp,
            textAlign = TextAlign.Center
        )
    }
}