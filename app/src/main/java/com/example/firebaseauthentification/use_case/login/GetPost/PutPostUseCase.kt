package com.example.firebaseauthentification.use_case.login.GetPost

import com.example.firebaseauthentification.data.repository.PostRepository
import javax.inject.Inject

class PutPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(id: String) = postRepository.putPost(id = id)
}