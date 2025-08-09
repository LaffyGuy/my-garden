package com.example.mygarden.core.exceptions

interface ExceptionToMessageMapper {

    fun getLocalizedMessage(exception: Exception): String

}