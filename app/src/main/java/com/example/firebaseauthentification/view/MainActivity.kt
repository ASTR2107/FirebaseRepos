package com.example.firebaseauthentification.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.firebaseauthentification.domain.model.repository.SaveViewSettingsImpl
import com.example.firebaseauthentification.ui.theme.FirebaseAuthentificationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saveViewSettingsImpl = SaveViewSettingsImpl(this)
        setContent {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val topBarTitle = remember {
                mutableStateOf("")
            }
            FirebaseAuthentificationTheme {
                MainTopBar()
                NavDrawer()


            }
        }

    }
}


