package com.example.firebaseauthentification.domain.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.firebaseauthentification.domain.repository.model.NetworkResult
import com.example.firebaseauthentification.domain.repository.model.UserModel
import com.example.firebaseauthentification.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private var auth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {
    val TAG = "AuthRepositoryImpl"

    @SuppressLint("SuspiciousIndentation")
    override suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {
            var isSuccess = false

            emit(NetworkResult.Loading())
            try {
                auth.createUserWithEmailAndPassword(user.login, user.password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmailAndPassword:: success")
                            val firebaseUser = auth.currentUser
                            if (firebaseUser != null) {
                                user.userId = auth.uid
                                firebaseFirestore
                                    .collection(Constants.USERS)
                                    .document(firebaseUser.uid)
                                    .set(user)
                            }
                            firebaseUser != null
                        } else {
                            Log.d(TAG, "createUserWithEmailAndPassword:: failure", task.exception)
                            false
                        }

                    }.await()

                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Oh , were"))
                }

            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(
                        message = e.localizedMessage ?: "Oh, something went wrong!"
                    )
                )
            }


        }
    }

    override suspend fun firebaseLogIn(
        email: String,
        password: String
    ): Flow<NetworkResult<Boolean>> {
        return flow {
            var isSuccess = false
            emit(NetworkResult.Loading())

            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.d(TAG, "singWithEmailPassword:: success")
                            auth.currentUser != null
                        } else {
                            Log.d(TAG, "singWithEmailPassword:: failure", task.exception)
                            false
                        }
                    }.await()
                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Oh , were"))
                }

            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(
                        message = e.localizedMessage ?: "Oh, something went wrong!"
                    )
                )
            }
        }
    }
}