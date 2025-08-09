package com.example.mygarden.core.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object GardensGraph {
    @Serializable
    data object GardensRoute

    @Serializable
    data object GardensConfiguratorRoute

    @Serializable
    data class GardenDetailsRoute(
        val gardenId: Long
    )
}

@Serializable
data object SearchGraph {
    @Serializable
    data object SearchRoute
}

@Serializable
data object SettingsGraph {
    @Serializable
    data object SettingsRoute
}





fun NavBackStackEntry?.routeClass(): KClass<*>? {
    return this?.destination.routeClass()
}

fun NavDestination?.routeClass(): KClass<*>? {
    return this?.route
        ?.split("/")
        ?.first()
        ?.let { className ->
            generateSequence(className, :: replaceLastDotByDollar)
                .mapNotNull(::tryParseClass)
                .firstOrNull()
        }
}

private fun tryParseClass(className: String): KClass<*>? {
    return runCatching { Class.forName(className).kotlin }.getOrNull()
}

private fun replaceLastDotByDollar(input: String): String? {
    val index = input.lastIndexOf('.')
    return if(index != -1){
        String(input.toCharArray().apply { set(index, '$') })
    } else {
        null
    }
}

