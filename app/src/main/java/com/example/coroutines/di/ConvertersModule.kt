package com.example.coroutines.di

import com.example.coroutines.viewmodel.GithubReducer
import org.koin.dsl.module

val reducerModule = module {
    factory { provideGithubReducer() }
}

private fun provideGithubReducer() = GithubReducer()