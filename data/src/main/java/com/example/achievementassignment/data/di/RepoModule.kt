package com.example.achievementassignment.data.di

import com.example.achievementassignment.data.api.ProjectApi
import com.example.achievementassignment.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Tells Hilt that this object contains instructions
// for creating Repository dependencies.
@Module

// Makes Repository available for the lifetime of the application.
@InstallIn(SingletonComponent::class)
object RepoModule {

    //Uses the ProjectApi created by NetworkModule
    // to create and provide one Repository instance.
    @Provides
    @Singleton
    fun provideRepository(
        projectApi: ProjectApi
    ): Repository{
        return Repository (projectApi)
    }
}