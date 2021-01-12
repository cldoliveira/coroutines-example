package com.example.coroutines.viewmodel

import androidx.lifecycle.*
import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import com.example.coroutines.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class GithubViewModel(private val repository: GithubRepository,
                      private val reducer: GithubReducer): ViewModel(), LifecycleObserver {

    val state = MutableLiveData<GithubState>().apply { value = GithubState() }

    private val currentState: GithubState?
        get() = state.value

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                state.postValue(reducer(currentState, Loading))
                val listOfUsers = repository.getUsers()
                state.postValue(reducer(currentState, ListReceived(listOfUsers)))
            } catch (ex: Exception) {
                state.postValue(reducer(currentState, ErrorReceived(ex.localizedMessage)))
            }
        }
    }
}