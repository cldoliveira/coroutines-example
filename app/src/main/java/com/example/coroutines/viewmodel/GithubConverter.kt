package com.example.coroutines.viewmodel

import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState

class GithubConverter {

    fun convert(currentState: GithubState?, event: GithubEvent): GithubState = currentState?.let { state ->
        when (event) {
            is Loading -> state.copy(errorMsg = "", lastEvent = event)
            is ErrorReceived -> state.copy(errorMsg = event.message, lastEvent = event)
            is ListReceived -> state.copy(listOfUser = event.listOfUser, lastEvent = event)
        }
    } ?: GithubState()
}