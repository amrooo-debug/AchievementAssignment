package com.example.achievementassignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achievementassignment.data.model.AchievementsResponseModel
import com.example.achievementassignment.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Tells Hilt that this ViewModel can receive dependencies.
@HiltViewModel
class MainViewModel @Inject constructor(
    // Hilt provides the Repository through the constructor.
    private val repository: Repository
) : ViewModel() {

    // Can be changed only inside this ViewModel.
    private val _achievementsSuccessLiveData =
        MutableLiveData<List<AchievementsResponseModel>>()

    // The UI can observe the data but cannot change it.
    val achievementsSuccessLiveData:
            LiveData<List<AchievementsResponseModel>> =
        _achievementsSuccessLiveData

    // Can be changed only inside this ViewModel.
    private val _achievementsErrorLiveData =
        MutableLiveData<Exception>()

    // The UI can observe errors but cannot change them.
    val achievementsErrorLiveData:
            LiveData<Exception> =
        _achievementsErrorLiveData

    // Holds whether the API request is currently running.
    // Only the ViewModel can change this value.
    private val _achievementsLoadingLiveData =
        MutableLiveData<Boolean>()

    // The UI can observe the loading state but cannot change it.
    val achievementsLoadingLiveData:
            LiveData<Boolean> =
        _achievementsLoadingLiveData

    // Requests achievement data when the ViewModel is created.
    init {
        getAchievements()
    }

    private fun getAchievements() {
        viewModelScope.launch {
            // Shows the loading indicator before starting the API request.
            _achievementsLoadingLiveData.value = true

            try {
                // Requests achievement data through the injected Repository.
                val response = repository.getAchievements()

                // Sends the successful response to the UI.
                _achievementsSuccessLiveData.value = response

                Log.d(
                    "AchievementVM",
                    "Achievement request successful. Sections = ${response.size}"
                )
            } catch (exception: Exception) {
                Log.e(
                    "AchievementVM",
                    "Achievement request failed",
                    exception
                )

                // Sends the error to the UI.
                _achievementsErrorLiveData.value = exception
            } finally {
                // Always hides the loading indicator after success or failure.
                _achievementsLoadingLiveData.value = false
            }
        }
    }
}