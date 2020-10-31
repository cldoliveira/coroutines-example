package com.example.coroutines

import android.app.Application
import com.example.coroutines.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule, adaptersModule, convertersModule))
        }
    }
}