package com.example.coroutines.di

import com.example.coroutines.repository.GithubRepository
import com.example.coroutines.viewmodel.GithubReducer
import com.example.coroutines.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { provideGithubViewModel(get(), get()) }
}

private fun provideGithubViewModel(repository: GithubRepository, reducer: GithubReducer) = GithubViewModel(repository, reducer)