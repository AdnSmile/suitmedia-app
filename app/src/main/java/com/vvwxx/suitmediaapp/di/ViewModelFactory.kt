package com.vvwxx.suitmediaapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vvwxx.suitmediaapp.data.AppRepository
import com.vvwxx.suitmediaapp.ui.first.FirstViewModel
import com.vvwxx.suitmediaapp.ui.third.UserViewModel

class ViewModelFactory(private val pref: AppRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FirstViewModel::class.java) -> {
                FirstViewModel(pref) as T
            }
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providerRepository(context))
            }.also { instance = it }
    }

}