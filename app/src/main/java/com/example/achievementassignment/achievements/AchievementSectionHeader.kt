package com.example.achievementassignment.ui.achievement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achievementassignment.ui.theme.AchievementSectionBackground
import com.example.achievementassignment.ui.theme.AchievementSectionLabel
import com.example.achievementassignment.ui.theme.AchievementSectionTitle

@Composable
fun AchievementSectionHeader( //making the headers like personal records
    // The section name received from the API, such as "Personal Records".
    title: String?,

    // The progress text received from the API, such as "4 of 6".
    label: String?
) {
    // Places the title and label horizontally in the same row.
    Row( //horizontal
        modifier = Modifier
            // Makes the header use the full width of the screen.
            .fillMaxWidth()

            // Applies the light gray section background from Color.kt.
            .background(AchievementSectionBackground)

            // Matches the horizontal and vertical spacing from the XML design.
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),

        // Vertically centers both text elements inside the row.
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            // Converts a null title into an empty string to prevent errors.
            text = title.orEmpty(),

            // Takes the available space and pushes the label to the right.
            modifier = Modifier.weight(1f),

            color = AchievementSectionTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            // Converts a null label into an empty string.
            text = label.orEmpty(),

            color = AchievementSectionLabel,
            fontSize = 14.sp
        )
    }
}
