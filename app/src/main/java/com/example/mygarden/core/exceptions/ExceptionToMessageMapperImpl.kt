package com.example.mygarden.core.exceptions

import com.example.mygarden.R
import com.example.mygarden.core.resource.DefaultStringResources
import javax.inject.Inject

class ExceptionToMessageMapperImpl @Inject constructor(private val stringResources: DefaultStringResources): ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: Exception): String {
        return when(exception) {
            is ConnectionException -> {
                stringResources.getString(R.string.connection_error_message)
            }
            is BackendException -> {
                stringResources.getString(R.string.backend_error_message)
            }
            else -> stringResources.getString(R.string.unknown_error_message)
        }
    }
}