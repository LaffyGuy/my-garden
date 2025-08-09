package com.example.mygarden.core.exceptions

import retrofit2.HttpException
import java.io.IOException


fun mapToAppException(e: Exception): AppExceptions = when(e) {
    is IOException -> ConnectionException(e)
    is HttpException -> {
        val errorBody = e.response()?.errorBody()?.string() ?: ""
        BackendException(
            httpCode = e.code(),
            backendMessage = errorBody,
            cause = e
        )
    }
    is AppExceptions -> e
    else -> UnknownException()
}