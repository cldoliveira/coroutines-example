package com.example.coroutines.viewmodel

import androidx.lifecycle.*
import com.example.coroutines.model.GithubEvent
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import com.example.coroutines.repository.GithubRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class GithubViewModel(private val repository: GithubRepository,
                      private val reducer: GithubReducer): ViewModel(), LifecycleObserver {

    val state = MutableLiveData<GithubState>().apply { value = GithubState() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                updateStateWhenEvent(Loading)
                val listOfUsers = repository.getUsers()
                updateStateWhenEvent(ListReceived(listOfUsers))
            } catch (ex: Exception) {
                updateStateWhenEvent(ErrorReceived(ex.localizedMessage))
            }
        }
    }

    private fun updateStateWhenEvent(event: GithubEvent) {
        reducer.convert(state.value, event).run {
            state.value = this
        }
    }
}