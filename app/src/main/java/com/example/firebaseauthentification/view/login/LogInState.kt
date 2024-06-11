package com.example.firebaseauthentification.view.login

data class LogInState (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
