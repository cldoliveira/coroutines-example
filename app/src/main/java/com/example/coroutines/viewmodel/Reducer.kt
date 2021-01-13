package com.example.coroutines.viewmodel

typealias Reducer<State, Event> =  (state: State?, event: Event) -> State