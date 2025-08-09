package com.example.mygarden.core.exceptions

abstract class AppExceptions(
    message: String,
    cause: Throwable? = null
): Exception(message, cause)

class UnknownException: AppExceptions("Unknown Exception")

class ConnectionException(
    cause: Throwable? = null,
) : AppExceptions("Network error", cause)

class BackendException(
    val httpCode: Int = 400,
    val backendMessage: String = "",
    cause: Throwable? = null
): AppExceptions("Server error", cause)