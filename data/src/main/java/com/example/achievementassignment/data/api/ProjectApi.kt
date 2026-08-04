package com.example.achievementassignment.data.api

import com.example.achievementassignment.data.model.AchievementsResponseModel
import com.example.achievementassignment.data.model.WrappedAchievementsResponseModel
import retrofit2.http.GET

interface  ProjectApi {

    @GET("/achievements")
    suspend fun getAchievements(): WrappedAchievementsResponseModel

}