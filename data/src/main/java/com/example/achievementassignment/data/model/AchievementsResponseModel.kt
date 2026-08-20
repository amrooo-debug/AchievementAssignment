package com.example.achievementassignment.data.model
import com.google.gson.annotations.SerializedName

data class AchievementsResponseModel(
    @SerializedName("id")
    val idAchievements : Int,

    val title : String,

    val label : String,

    val records : List <RecordsModel>

)
