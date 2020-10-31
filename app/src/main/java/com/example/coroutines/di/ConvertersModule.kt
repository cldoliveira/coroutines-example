package com.example.coroutines.di

import com.example.coroutines.viewmodel.GithubConverter
import org.koin.dsl.module

val convertersModule = module {
    factory { provideGithubConverter() }
}

private fun provideGithubConverter() = GithubConverter()