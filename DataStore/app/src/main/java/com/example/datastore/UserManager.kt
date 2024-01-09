package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserManager (context: Context){

}