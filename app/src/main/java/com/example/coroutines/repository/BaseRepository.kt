package com.example.coroutines.repository

import retrofit2.HttpException

open class BaseRepository {

    protected suspend fun <T> getResult(call: suspend () -> T): Result<T> {
        return try {
            Result.Success(call())
        } catch (exception: Exception) {
            if (exception is HttpException) {
                Result.Error(exception, exception.code())
            } else {
                Result.Error(exception)
            }
        }
    }
}