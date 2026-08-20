package com.example.achievementassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Starts Hilt when the application process is created.
@HiltAndroidApp
class AchievementApplication : Application()