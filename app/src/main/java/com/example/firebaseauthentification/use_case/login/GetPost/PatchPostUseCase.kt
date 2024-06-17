package com.example.firebaseauthentification.use_case.login.GetPost

import com.example.firebaseauthentification.data.repository.PostRepository
import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import javax.inject.Inject

class PatchPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(id: String,body: PostResponse) = postRepository.patchPost(id = id, body = body)
}