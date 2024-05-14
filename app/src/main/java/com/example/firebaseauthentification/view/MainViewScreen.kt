package com.example.firebaseauthentification.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaseauthentification.domain.model.data.SettingsData
import com.example.firebaseauthentification.domain.model.repository.SaveViewSettingsImpl
import com.example.firebaseauthentification.ui.theme.Blue
import com.example.firebaseauthentification.ui.theme.Green
import com.example.firebaseauthentification.ui.theme.White
import com.example.firebaseauthentification.viewModel.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun MainViewScreen(
    vm: MyViewModel = viewModel(),
    saveViewSettings: SaveViewSettingsImpl,
    textSize: Int
) {
    val coroutine = rememberCoroutineScope()
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize(0.5f)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
            Text(
                text = "Some Text",
                color = Color.White,
                fontSize = textSize.sp
            )
        }
        Button(
            onClick = {
                coroutine.launch {
                    saveViewSettings.saveSettings(
                        SettingsData(
                            10,
                            Blue.value
                        )
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {

        }
        Button(onClick = {
            coroutine.launch {
                saveViewSettings.saveSettings(
                    SettingsData(
                        30,
                        Green.value
                    )
                )
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green) ) {

        }
        Button(onClick = {
            coroutine.launch {
                saveViewSettings.saveSettings(
                    SettingsData(
                        10,
                        White.value
                    )
                )
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {

        }

    }

}

