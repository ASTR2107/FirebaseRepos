package com.example.firebaseauthentification.data.api

import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostService {
    @GET("/post")
    suspend fun gelAllPosts(): Response<List<PostResponse>>

    @POST("/posts")
    suspend fun postAndPost(@Body body: PostResponse): Response<PostResponse>

    @PUT
    suspend fun putPost(@Path("id") id: String): Response<PostResponse>

    @PATCH
    suspend fun patchPost(@Path("id") id: String): Response<PostResponse>

    @DELETE
    suspend fun deletePost(@Path("id") id: String): Response<PostResponse>
}