package com.example.coroutines.viewmodel

import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubReducer {

    suspend operator fun invoke(currentState: GithubState?, event: GithubEvent): GithubState = withContext(Dispatchers.Default) {
        currentState?.let { state ->
            when (event) {
                is Loading -> state.copy(errorMsg = "", lastEvent = event)
                is ErrorReceived -> state.copy(errorMsg = event.message, lastEvent = event)
                is ListReceived -> state.copy(listOfUser = event.listOfUser, lastEvent = event)
            }
        } ?: GithubState()
    }
}