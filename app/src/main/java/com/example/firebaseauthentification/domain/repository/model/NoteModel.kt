package com.example.firebaseauthentification.domain.repository.model

import java.time.LocalDate

data class NoteModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val data: String,
    val author: String
)
