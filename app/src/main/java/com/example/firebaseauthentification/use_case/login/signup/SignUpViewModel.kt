package com.example.firebaseauthentification.use_case.login.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentification.utils.NetworkResult
import com.example.firebaseauthentification.domain.repository.AuthRepository
import com.example.firebaseauthentification.use_case.login.login.LogInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val _firebaseSignUpState = Channel<LogInState>()
    val signUpState = _firebaseSignUpState.receiveAsFlow()

    fun singUpUser(email: String, password: String) = viewModelScope.launch {
        repository.firebaseLogIn(email, password).collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _firebaseSignUpState.send(LogInState(isSuccess = "Sign in Success"))
                }

                is NetworkResult.Loading -> {
                    _firebaseSignUpState.send(LogInState(isLoading = true))
                }

                is NetworkResult.Error -> {
                    _firebaseSignUpState.send(LogInState(isError = result.message))
                }
            }
        }
    }
}


