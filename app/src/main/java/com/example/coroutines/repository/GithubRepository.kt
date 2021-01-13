package com.example.coroutines.repository

import com.example.coroutines.network.GithubApi

class GithubRepository(private val api: GithubApi): BaseRepository() {

    suspend fun getUsers() = getResult { api.getUsers() }
}