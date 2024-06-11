package com.example.firebaseauthentification.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentification.domain.model.data.NetworkResult
import com.example.firebaseauthentification.domain.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val _firebaseLogInState = Channel<LogInState>()
    val logInState = _firebaseLogInState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.firebaseLogIn(email, password).collect{result ->
           when(result){
               is NetworkResult.Success -> {
                   _firebaseLogInState.send(LogInState(isSuccess = "Sign in Success"))
               }
               is NetworkResult.Loading -> {
                   _firebaseLogInState.send(LogInState(isLoading = true ))
               }
               is NetworkResult.Error -> {
                   _firebaseLogInState.send(LogInState(isError = result.message))
               }
           }
        }
    }


}
