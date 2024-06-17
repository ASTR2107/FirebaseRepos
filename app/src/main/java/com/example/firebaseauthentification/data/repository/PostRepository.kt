package com.example.firebaseauthentification.data.repository

import com.example.firebaseauthentification.data.api.RemoteDataSource
import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import com.example.firebaseauthentification.utils.BaseApiResponse
import com.example.firebaseauthentification.utils.NetworkResult
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse() {
    suspend fun getAllPosts(): NetworkResult<List<PostResponse>>{
        return safeApiCall { remoteDataSource.getAllPosts() }
    }
    suspend fun postAndPost(body: PostResponse): NetworkResult<PostResponse>{
        return safeApiCall { remoteDataSource.postAndPost(body = body) }
    }
    suspend fun putPost(id: String, body: PostResponse): NetworkResult<PostResponse>{
        return safeApiCall { remoteDataSource.putPost(id = id, body = body) }
    }
    suspend fun patchPost(id: String, body: PostResponse): NetworkResult<PostResponse>{
        return safeApiCall { remoteDataSource.patchPost(id = id, body = body) }
    }
    suspend fun deletePost(id: String): NetworkResult<PostResponse>{
        return safeApiCall { remoteDataSource.deletePost(id = id) }
    }
}