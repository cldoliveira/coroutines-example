package com.example.coroutines.model

import com.example.coroutines.network.User

sealed class GithubEvent {
    object Loading: GithubEvent()
    data class ListReceived(val listOfUser: List<User>): GithubEvent()
    data class ErrorReceived(val message: String?): GithubEvent()
}

data class GithubState(
    val listOfUser: List<User> = emptyList(),
    val errorMsg: String? = "",
    val lastEvent: GithubEvent? = null
)