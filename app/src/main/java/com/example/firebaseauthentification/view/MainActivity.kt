package com.example.firebaseauthentification.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.firebaseauthentification.domain.repository.SaveViewSettingsImpl
import com.example.firebaseauthentification.view.theme.FirebaseAuthentificationTheme
import com.example.firebaseauthentification.view.theme.navigation.AppNavigation
import com.example.firebaseauthentification.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saveViewSettingsImpl = SaveViewSettingsImpl(this)
        installSplashScreen().apply {
        }
        setContent {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val topBarTitle = remember {
                mutableStateOf("")
            }
            FirebaseAuthentificationTheme {
                AppNavigation()
            }
        }
    }
}





