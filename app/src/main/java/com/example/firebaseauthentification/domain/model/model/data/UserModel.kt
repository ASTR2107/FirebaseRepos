package com.example.firebaseauthentification.domain.model.model.data

data class UserModel(
    var userId: String? = null,
    var login: String,
    var password: String,
    val firstName: String,
    val lastName: String,
    val about: String? = null
)
