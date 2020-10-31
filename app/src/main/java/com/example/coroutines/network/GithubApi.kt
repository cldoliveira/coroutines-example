package com.example.coroutines.network

import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<User>
}