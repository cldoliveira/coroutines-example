package com.example.coroutines.di

import com.example.coroutines.ui.GithubAdapter
import org.koin.dsl.module

val adaptersModule = module {
    factory { provideGithubAdapter() }
}

private fun provideGithubAdapter() = GithubAdapter()