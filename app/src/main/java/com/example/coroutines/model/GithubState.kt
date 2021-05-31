package com.example.coroutines.model

import android.view.View
import com.example.coroutines.network.User

open class State

sealed class GithubEvent {
    object ShowLoading: GithubEvent()
    data class ListReceived(val listOfUser: List<User>): GithubEvent()
    data class ErrorReceived(val message: String?): GithubEvent()
}

data class GithubState(
    val listOfUser: List<User> = emptyList(),
    val errorMsg: String? = "",
    val progressVisibility: Int = View.GONE,
    val lastEvent: GithubEvent? = null
): State()