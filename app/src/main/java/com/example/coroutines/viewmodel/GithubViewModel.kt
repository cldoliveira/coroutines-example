package com.example.coroutines.viewmodel

import androidx.lifecycle.*
import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import kotlinx.coroutines.launch

class GithubViewModel(private val getUsersUseCase: GetUsersUseCase,
                      reducer: GithubReducer): BaseViewModel<GithubState, GithubEvent>(reducer), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchUsers() {
        viewModelScope.launch {
            updateState(ShowLoading)

            /* try to fetch data from server */
            val githubUsers = getUsersUseCase()

            /* update the state when the search has finished */
            updateState(githubUsers)
        }
    }

    override fun setInitialState() = GithubState()
}