package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserManager (context: Context){
    private val USER_AGE_KEY = intPreferencesKey("USER_AGE")
    private val USER_NAME_KEY = stringPreferencesKey("USER_NAME_KEY")

    suspend fun storeUserAge(age: Int, name: String, context: Context){
        context.dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_NAME_KEY] = name
        }
    }

    val userAgeFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[USER_AGE_KEY] ?: 0
    }
    val userNameFlow: Flow<String> = context.dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

}