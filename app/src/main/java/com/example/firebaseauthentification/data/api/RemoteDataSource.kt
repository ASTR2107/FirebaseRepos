package com.example.firebaseauthentification.data.api

import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postService: PostService) {
    suspend fun getAllPosts() = postService.gelAllPosts()
    suspend fun postAndPost(body: ResponseBody) = postService.postAndPost(body = body)
    suspend fun putPost(id: String, body: PostResponse) = postService.putPost(id = id, body = body)
    suspend fun patchPost(id: String, body: PostResponse) = postService.patchPost(id = id, body = body)
    suspend fun deletePost(id: String) = postService.deletePost(id = id)

}