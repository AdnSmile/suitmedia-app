package com.vvwxx.suitmediaapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.vvwxx.suitmediaapp.data.response.User
import com.vvwxx.suitmediaapp.data.retrofit.ApiService
import com.vvwxx.suitmediaapp.di.UiState
import com.vvwxx.suitmediaapp.model.DataModel
import com.vvwxx.suitmediaapp.model.UserPreferences

class AppRepository(
    private val apiService: ApiService,
    private val pref: UserPreferences,
) {

    private val _usersResponse = MutableLiveData<UiState<List<User>>>(UiState.Loading)
    val usersResponse: LiveData<UiState<List<User>>>
        get() = _usersResponse

    suspend fun getUserApi(page: Int, perPage: Int) {
        try {
            val response = apiService.getUsers(page, perPage)
            _usersResponse.value = UiState.Success(response.data)
        } catch (e: Exception) {
            _usersResponse.value = UiState.Error(e.message.toString())
        }
    }

    fun getPref(): LiveData<DataModel> = pref.getPreference().asLiveData()

    suspend fun setName(name: String) {
        pref.setName(name)
    }

    suspend fun setSelectedName(name: String) {
        pref.setSelectedName(name)
    }

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(apiService: ApiService, pref: UserPreferences): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(apiService, pref)
            }.also { instance = it }
    }
}