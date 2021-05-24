package com.example.coroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel<State, Event>(private val reducer: Reducer<State, Event>): ViewModel() {
    val state = MutableLiveData<State>().apply { value = setInitialState() }

    private val currentState: State
        get() = state.value ?: setInitialState()

    abstract fun setInitialState(): State

    protected fun updateState(newEvent: Event) {
        state.value = reducer(currentState, newEvent)
    }
}