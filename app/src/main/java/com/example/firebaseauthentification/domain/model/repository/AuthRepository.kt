package com.example.firebaseauthentification.domain.model.repository

import com.example.firebaseauthentification.domain.model.data.NetworkResult
import com.example.firebaseauthentification.domain.model.data.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>>
    suspend fun firebaseLogIn(email: String,password: String): Flow<NetworkResult<Boolean>>


}
