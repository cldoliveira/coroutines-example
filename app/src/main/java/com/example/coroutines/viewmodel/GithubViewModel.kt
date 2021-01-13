package com.example.coroutines.viewmodel

import androidx.lifecycle.*
import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import com.example.coroutines.network.User
import com.example.coroutines.repository.GithubRepository
import com.example.coroutines.repository.Result
import kotlinx.coroutines.launch

class GithubViewModel(private val repository: GithubRepository,
                      reducer: GithubReducer): BaseViewModel<GithubState, GithubEvent>(reducer), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchUsers() {
        viewModelScope.launch {
            updateState(ShowLoading)
            when (val result = repository.getUsers()) {
                is Result.Success<List<User>> -> updateState(ListReceived(result.data))
                is Result.Error ->  updateState(ErrorReceived(result.exception.localizedMessage))
            }
        }
    }

    override fun setInitialState() = GithubState()
}