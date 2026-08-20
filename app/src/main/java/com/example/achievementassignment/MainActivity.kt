package com.example.achievementassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.achievementassignment.ui.achievement.AchievementScreen
import com.example.achievementassignment.ui.theme.AchievementAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

// Allows MainActivity and its Compose content to use Hilt dependencies.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Runs when MainActivity is created and the app screen opens.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Starts the Jetpack Compose UI.
        // This replaces setContentView() used in XML projects.
        setContent {
            // Applies the app colors, typography, and Compose theme.
            AchievementAssignmentTheme {
                // Opens the main Achievement Compose screen.
                AchievementScreen()
            }
        }
    }
}