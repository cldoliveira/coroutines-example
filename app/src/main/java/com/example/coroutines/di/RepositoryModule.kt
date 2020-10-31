package com.example.coroutines.di

import com.example.coroutines.network.GithubApi
import com.example.coroutines.repository.GithubRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideGithubRepository(get()) }
}

private fun provideGithubRepository(api: GithubApi) = GithubRepository(api)