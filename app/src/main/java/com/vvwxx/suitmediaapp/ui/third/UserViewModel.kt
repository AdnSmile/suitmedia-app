package com.vvwxx.suitmediaapp.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.suitmediaapp.data.AppRepository
import com.vvwxx.suitmediaapp.data.response.User
import com.vvwxx.suitmediaapp.di.UiState
import kotlinx.coroutines.launch

class UserViewModel(private val repo: AppRepository) : ViewModel() {

    val getPref = repo.getPref()

    val listUsersResponse: LiveData<UiState<List<User>>> get() = repo.usersResponse

    fun getUserList(page: Int, totalPage: Int) {
        viewModelScope.launch { repo.getUserApi(page, totalPage) }
    }

}