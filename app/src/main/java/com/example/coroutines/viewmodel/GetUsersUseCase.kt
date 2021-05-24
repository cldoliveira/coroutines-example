package com.example.coroutines.viewmodel

import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.network.User
import com.example.coroutines.repository.GithubRepository
import com.example.coroutines.repository.Result

class GetUsersUseCase(private val repository: GithubRepository) {

    suspend operator fun invoke(): GithubEvent {
        return when (val response = repository.getUsers()) {
            is Result.Success<List<User>> -> ListReceived(response.data)
            is Result.Error -> ErrorReceived(response.exception.localizedMessage)
        }
    }
}