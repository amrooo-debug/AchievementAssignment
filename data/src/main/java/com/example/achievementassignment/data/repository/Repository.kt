package com.example.achievementassignment.data.repository

import com.example.achievementassignment.data.api.ProjectApi
import com.example.achievementassignment.data.model.AchievementsResponseModel

class Repository(
    // ProjectApi will be provided by Hilt instead of being created here.
    private val projectApi: ProjectApi
) {

    suspend fun getAchievements(): List<AchievementsResponseModel> {
        // Uses the ProjectApi instance received through the constructor.
        return projectApi.getAchievements().data
    }
}