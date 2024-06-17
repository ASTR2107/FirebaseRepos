package com.example.firebaseauthentification.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firebaseauthentification.utils.ErrorScreen
import com.example.firebaseauthentification.utils.LoadingScreen
import com.example.firebaseauthentification.utils.NetworkResult
import com.example.firebaseauthentification.viewModel.MainViewModel

@Composable
fun ApiScreen(mainViewModel: MainViewModel) {
    val state = mainViewModel.allPostResponse.value ?: NetworkResult.Loading()
    when(state) {
        is NetworkResult.Success<*> -> {
            SuccessScreen(state.data ?: listOf(), mainViewModel)
        }
        is NetworkResult.Error<*> -> {
            ErrorScreen(state.message?: "Error")

        }
        is NetworkResult.Loading<*> -> {
            LoadingScreen()
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "test 1")
    }
}


