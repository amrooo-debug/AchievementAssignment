package com.example.achievementassignment.ui.achievement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.achievementassignment.data.model.RecordsModel

@Composable
fun AchievementMedalItem( //making the images and their info
    // One achievement record received from the API.
    record: RecordsModel
) {
    // Places the image, title, and label vertically.
    Column( //vertical
        modifier = Modifier
            // Makes every grid item use the full width of its column.
            .fillMaxWidth()

            // Shows active achievements normally and inactive ones faded.
            // This replaces android:alpha from the XML layout.
            .alpha(
                if (record.active) {
                    1f
                } else {
                    0.5f
                }
            )

            // Adds space above and below each achievement item.
            .padding(vertical = 20.dp),

        // Centers the image and text horizontally.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Loads the achievement image from the URL returned by the API.
        AsyncImage(
            model = record.image,

            // Used by accessibility services to describe the image.
            contentDescription = record.title,

            // Matches the 110dp image size used in the XML design.
            modifier = Modifier.size(110.dp),

            // Keeps the complete image visible without cropping it.
            contentScale = ContentScale.Fit
        )

        Text(
            // display the title
            // Achievement name, such as "Longest Run".
            // orEmpty() prevents a null value from causing a problem.
            text = record.title.orEmpty(),

            // Adds spacing on both sides of the text.
            modifier = Modifier.padding(horizontal = 10.dp),

            color = Color.Black,
            fontSize = 14.sp,

            // Centers long titles inside the grid column.
            textAlign = TextAlign.Center
        )

        Text(
            // Achievement value, such as "00:00" or "2095 ft".
            text = record.label.orEmpty(),

            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}
