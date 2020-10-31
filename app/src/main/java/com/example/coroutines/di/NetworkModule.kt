package com.example.coroutines.di

import com.example.coroutines.network.GithubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 45L

val networkModule = module {
    single { provideUrl() }
    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get(), get()) }
    single { provideGithubApi(get()) }
}

private fun provideUrl() = "https://api.github.com/"

private fun provideGson() = GsonBuilder().create()

private fun provideOkHttpClient() = OkHttpClient.Builder().run {
    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    readTimeout(TIMEOUT, TimeUnit.SECONDS)
    connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    addInterceptor(logging)
    build()
}

private fun provideRetrofit(baseURL: String, client: OkHttpClient, gson: Gson) = Retrofit.Builder().run {
    baseUrl(baseURL)
    client(client)
    addConverterFactory(GsonConverterFactory.create(gson))
    build()
}

fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)