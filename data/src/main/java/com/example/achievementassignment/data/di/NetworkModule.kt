package com.example.achievementassignment.data.di

import com.example.achievementassignment.data.api.ProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Tells Hilt that this object contains instructions
// for creating and providing dependencies.
@Module

// Makes the provided dependencies available
// during the full lifetime of the application.
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base address used by Retrofit for every API request.
    private const val BASE_URL =
        "https://786b905e-735c-4be6-adfb-949d5dadee32.mock.pstmn.io/"

    // Tells Hilt that this function provides a Retrofit object.
    @Provides

    // Creates Retrofit once and reuses the same instance.
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Converts JSON responses into Kotlin model objects.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // NEW: Uses the provided Retrofit instance to create ProjectApi.
    @Provides
    @Singleton
    fun provideProjectApi(retrofit: Retrofit): ProjectApi {
        return retrofit.create(ProjectApi::class.java)
    }
}