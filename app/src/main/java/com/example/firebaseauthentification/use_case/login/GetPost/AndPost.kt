package com.example.firebaseauthentification.use_case.login.GetPost

import com.example.firebaseauthentification.data.repository.PostRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class AndPost @Inject constructor(
    private val postRepository: PostRepository
){
    suspend fun execute(body: ResponseBody) = postRepository.postAndPost(body = body)
}