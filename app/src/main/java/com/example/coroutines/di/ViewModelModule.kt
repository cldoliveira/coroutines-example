package com.example.coroutines.di

import com.example.coroutines.viewmodel.GetUsersUseCase
import com.example.coroutines.viewmodel.GithubReducer
import com.example.coroutines.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { provideGithubViewModel(get(), get()) }
}

private fun provideGithubViewModel(getUsersUseCase: GetUsersUseCase, reducer: GithubReducer) = GithubViewModel(getUsersUseCase, reducer)