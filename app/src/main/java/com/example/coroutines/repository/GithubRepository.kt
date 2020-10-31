package com.example.coroutines.repository

import com.example.coroutines.network.GithubApi

class GithubRepository(private val api: GithubApi) {

    suspend fun getUsers() = api.getUsers()
}