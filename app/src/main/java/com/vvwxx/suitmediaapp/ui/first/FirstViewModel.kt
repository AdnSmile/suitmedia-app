package com.vvwxx.suitmediaapp.ui.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.suitmediaapp.data.AppRepository
import kotlinx.coroutines.launch

class FirstViewModel(private val repo: AppRepository) : ViewModel()  {

    val getPref = repo.getPref()

    fun setNamePref(name: String) {
        viewModelScope.launch {
            repo.setName(name)
        }
    }

    fun setSelectedNamePref(name: String) {
        viewModelScope.launch {
            repo.setSelectedName(name)
        }
    }

}