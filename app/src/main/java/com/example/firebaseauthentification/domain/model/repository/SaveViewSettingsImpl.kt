package com.example.firebaseauthentification.domain.model.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.firebaseauthentification.domain.model.data.SettingsData
import com.example.firebaseauthentification.ui.theme.Blue
import com.example.firebaseauthentification.ui.theme.White
import kotlinx.coroutines.flow.map

class SaveViewSettingsImpl(private val context: Context) : SaveViewSettings {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore("data store")

    override suspend fun saveSettings(settingsData: SettingsData) {
        context.datastore.edit { pref ->
            pref[intPreferencesKey("text_size")] = settingsData.textSize
            pref[intPreferencesKey("bg_color")] = settingsData.bgColor.toLong().toInt()
        }
    }

    override suspend fun getSettings() = context.datastore.data.map { pref ->
        return@map SettingsData(
            pref[intPreferencesKey("text_size")] ?: 50,
            pref[intPreferencesKey("bg_color")] ?. toULong() ?: White.value
        )
    }

}