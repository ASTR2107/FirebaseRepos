package com.example.firebaseauthentification.domain.model.repository

import com.example.firebaseauthentification.domain.model.data.SettingsData
import kotlinx.coroutines.flow.Flow

interface SaveViewSettings {
    suspend fun saveSettings(settingsData: SettingsData)
    suspend fun getSettings(): Flow<SettingsData>

}