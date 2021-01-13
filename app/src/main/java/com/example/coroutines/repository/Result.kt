package com.example.coroutines.repository

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val status: Int = 0) : Result<Nothing>()
}