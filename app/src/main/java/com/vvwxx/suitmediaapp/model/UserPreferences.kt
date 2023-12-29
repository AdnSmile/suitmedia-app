package com.vvwxx.suitmediaapp.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getPreference(): Flow<DataModel> {
        return dataStore.data.map {
            DataModel(
                it[NAME_KEY] ?: "",
                it[SELECTED_NAME_KEY] ?: ""
            )
        }
    }

    suspend fun setName(name: String) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    suspend fun setSelectedName(name: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_NAME_KEY] = name
        }
    }

    suspend fun clearPref() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val SELECTED_NAME_KEY = stringPreferencesKey("selected_name")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}