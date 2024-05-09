package com.example.firebaseauthentification.domain.repository.model

data class Book(
    val name: String? = null,
    val description: String,
    val price: String,
    val category: String,
    val imageUrl: String
)
