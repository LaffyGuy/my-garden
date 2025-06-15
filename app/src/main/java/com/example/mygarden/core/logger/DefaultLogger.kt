package com.example.mygarden.core.logger

import timber.log.Timber
import javax.inject.Inject

class DefaultLogger @Inject constructor(): Logger {

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun e(exception: Exception, message: String) {
        Timber.d(exception, message)
    }
}