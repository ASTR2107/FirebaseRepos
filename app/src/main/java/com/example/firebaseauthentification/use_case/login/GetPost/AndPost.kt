package com.example.firebaseauthentification.use_case.login.GetPost

import com.example.firebaseauthentification.data.repository.PostRepository
import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import javax.inject.Inject

class AndPost @Inject constructor(
    private val postRepository: PostRepository
){
    suspend fun execute(body: PostResponse) = postRepository.postAndPost(body = body)
}