package com.example.mygarden.core.resource

interface StringResources {

    fun getString(
        resId: Int,
        vararg args: Any?
    ): String

}