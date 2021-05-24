package com.example.coroutines.di

import com.example.coroutines.repository.GithubRepository
import com.example.coroutines.viewmodel.GetUsersUseCase
import org.koin.dsl.module

val userCaseModule = module {
    single { provideGetUsersUseCase(get()) }
}

private fun provideGetUsersUseCase(repository: GithubRepository) = GetUsersUseCase(repository)