package com.example.coroutines.viewmodel

import android.view.View
import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState

class GithubReducer: Reducer<GithubState, GithubEvent> {

    override operator fun invoke(state: GithubState, event: GithubEvent): GithubState {
        return when (event) {
            is ShowLoading -> state.copy(progressVisibility = View.VISIBLE, lastEvent = event)
            is ErrorReceived -> state.copy(errorMsg = event.message, lastEvent = event)
            is ListReceived -> state.copy(listOfUser = event.listOfUser, errorMsg = "" , progressVisibility = View.GONE, lastEvent = event)
        }
    }
}