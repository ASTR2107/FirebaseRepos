package com.example.firebaseauthentification.model

data class UserModel(
    var userId: String? = null,
    var login: String,
    var password: String,
    val firstName: String,
    val lastName: String,
    val about: String? = null
)
