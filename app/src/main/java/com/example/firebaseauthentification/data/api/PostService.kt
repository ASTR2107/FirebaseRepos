package com.example.firebaseauthentification.data.api

import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import okhttp3.ResponseBody
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
    suspend fun postAndPost(@Body body: ResponseBody): Response<PostResponse>

    @PUT("/posts/{id}")
    suspend fun putPost(@Path("id") id: String, @Body body: PostResponse): Response<PostResponse>

    @PATCH("/posts/{id}")
    suspend fun patchPost(@Path("id") id: String, @Body body: PostResponse): Response<PostResponse>

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: String): Response<PostResponse>
}