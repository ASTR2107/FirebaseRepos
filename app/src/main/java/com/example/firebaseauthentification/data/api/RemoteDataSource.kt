package com.example.firebaseauthentification.data.api

import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postService: PostService) {
    suspend fun getAllPosts() = postService.gelAllPosts()
    suspend fun postAndPost(body: PostResponse) = postService.postAndPost(body = body)
    suspend fun putPost(id: String) = postService.putPost(id = id)
    suspend fun patchPost(id: String) = postService.patchPost(id = id)
    suspend fun deletePost(id: String) = postService.deletePost(id = id)

}