package com.example.firebaseauthentification.use_case.login.GetPost

import com.example.firebaseauthentification.data.repository.PostRepository
import javax.inject.Inject

class GetPost @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute() = postRepository.getAllPosts()
}