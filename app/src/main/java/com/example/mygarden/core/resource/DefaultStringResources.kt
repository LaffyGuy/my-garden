package com.example.mygarden.core.resource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultStringResources @Inject constructor(@ApplicationContext private val context: Context): StringResources {

    override fun getString(resId: Int, vararg args: Any?): String {
        return context.getString(resId, *args)
    }
}