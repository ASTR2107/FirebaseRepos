package com.example.firebaseauthentification.use_case.login.login

data class LogInState (
    val isLoading: Boolean = false,
    val isSuccess: String? = null,
    val isError: String? = ""
)
