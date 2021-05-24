package com.example.coroutines.viewmodel

import androidx.lifecycle.*
import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import com.example.coroutines.network.User
import com.example.coroutines.repository.GithubRepository
import com.example.coroutines.repository.Result
import kotlinx.coroutines.launch

class GithubViewModel(private val getUsersUseCase: GetUsersUseCase,
                      reducer: GithubReducer): BaseViewModel<GithubState, GithubEvent>(reducer), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchUsers() {
        viewModelScope.launch {
            updateState(ShowLoading)

            /* try to fetch data from server */
            val fetchUsersState = getUsersUseCase()

            /* update the state when the search has finished */
            updateState(fetchUsersState)
        }
    }

    override fun setInitialState() = GithubState()
}