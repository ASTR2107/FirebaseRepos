package com.example.firebaseauthentification.domain.repository

import com.example.firebaseauthentification.domain.repository.model.NetworkResult
import com.example.firebaseauthentification.domain.repository.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>>
    suspend fun firebaseLogIn(email: String,password: String): Flow<NetworkResult<Boolean>>


}
