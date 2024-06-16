package com.example.firebaseauthentification.domain.repository

import com.example.firebaseauthentification.domain.model.model.data.SettingsData
import kotlinx.coroutines.flow.Flow

interface SaveViewSettings {
    suspend fun saveSettings(settingsData: SettingsData)
    suspend fun getSettings(): Flow<SettingsData>

}