package com.example.firebaseauthentification.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.firebaseauthentification.domain.model.data.SettingsData
import com.example.firebaseauthentification.domain.model.repository.SaveViewSettingsImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saveViewSettingsImpl = SaveViewSettingsImpl(this)
        setContent {
            val settingsState = saveViewSettingsImpl
                .getSettings()
                .collectAsState(initial = SettingsData)
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(settingsState.value.bgColor)
            ) {

            }
            MainViewScreen(saveViewSettingsImpl, settingsState.value.textSize)
        }
    }
}

