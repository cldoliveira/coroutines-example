package com.example.coroutines.viewmodel

import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState

class GithubConverter {

    fun convert(currentState: GithubState?, event: GithubEvent): GithubState = currentState?.let { state ->
        when (event) {
            is Loading -> state.copy(errorMsg = "", lastEvent = event)
            is ErrorReceived -> state.copy(errorMsg = state.errorMsg, lastEvent = event)
            is ListReceived -> state.copy(listOfUser = state.listOfUser, lastEvent = event)
        }
    } ?: GithubState()
}