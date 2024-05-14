package com.example.firebaseauthentification.viewModel

import android.content.Context
import android.hardware.camera2.params.ColorSpaceTransform
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentification.domain.model.data.SettingsData
import com.example.firebaseauthentification.domain.model.repository.SaveViewSettings
import com.example.firebaseauthentification.ui.theme.Blue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
@HiltViewModel
class MyViewModel(): ViewModel() {
    var newColor by mutableStateOf(Color.Red)
    }
