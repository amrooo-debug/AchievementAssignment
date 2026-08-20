package com.example.achievementassignment.ui.achievement

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.achievementassignment.MainViewModel
import com.example.achievementassignment.data.model.AchievementsResponseModel
import com.example.achievementassignment.ui.theme.AchievementPrimary

// Builds the complete Achievement screen using Jetpack Compose.
@Composable
fun AchievementScreen(
    // Gets the existing MainViewModel or creates it through Hilt.
    mainViewModel: MainViewModel = viewModel()
) {
    // Observes the successful API response.
    // An empty list is shown before the response arrives.
    val achievements by mainViewModel
        .achievementsSuccessLiveData
        .observeAsState(emptyList())

    // Observes errors returned by the API request.
    val error by mainViewModel
        .achievementsErrorLiveData
        .observeAsState()

    // Observes whether the API request is currently running.
    val isLoading by mainViewModel
        .achievementsLoadingLiveData
        .observeAsState(false)

    // Provides the Android Context required to display a Toast.
    val context = LocalContext.current

    // Runs whenever a new error value is received.
    LaunchedEffect(error) {
        error?.let { exception ->
            Toast.makeText(
                context,
                "Error: ${exception.localizedMessage ?: "Unknown error"}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Box allows the loading indicator to appear above the screen content.
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Places the top bar and achievement grid vertically.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AchievementTopBar()

            // Displays the achievement sections and records from the API.
            AchievementGrid(
                achievements = achievements
            )
        }

        // Compose replacement for the tutor's XML ProgressBar.
        // It appears only while the API request is running.
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = AchievementPrimary
            )
        }
    }
}

// Creates the teal bar displayed at the top of the screen.
@Composable
private fun AchievementTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(AchievementPrimary)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Achievement",

            // Uses the remaining space and pushes the menu icon to the end.
            modifier = Modifier.weight(1f),

            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        // Temporary three-dot menu icon.
        // It does not perform an action yet.
        Text(
            text = "⋮",
            color = Color.White,
            fontSize = 28.sp
        )
    }
}

// Displays achievement records in two columns.
@Composable
private fun AchievementGrid(
    achievements: List<AchievementsResponseModel>
) {
    // Compose replacement for RecyclerView with GridLayoutManager.
    LazyVerticalGrid(
        // Creates two columns for achievement medals.
        columns = GridCells.Fixed(2),

        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(
            bottom = 5.dp
        )
    ) {
        // Goes through every achievement section returned by the API.
        achievements.forEach { achievement ->

            // Adds a full-width header for the current section,
            // for example "Personal Records".
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                AchievementSectionHeader(
                    title = achievement.title,
                    label = achievement.label
                )
            }

            // Displays every record belonging to the current section.
            items(
                items = achievement.records.orEmpty()
            ) { record ->
                AchievementMedalItem(
                    record = record
                )
            }
        }
    }
}