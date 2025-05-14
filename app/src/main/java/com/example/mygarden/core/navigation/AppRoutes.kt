package com.example.mygarden.core.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object GardensRoute

//fun NavBackStackEntry?.routeClass(): KClass<*>? {
//    return this.routeClass()
//}
//
//fun NavDestination?.routeClass(): KClass<*>? {
//    return this?.route
//        ?.split("/")
//        ?.first()
//        ?.let { className ->
//            generateSequence(className, :: replaceLastDotByDollar)
//                .mapNotNull(::tryParseClass)
//                .firstOrNull()
//        }
//}
//
//private fun tryParseClass(className: String): KClass<*>? {
//    return runCatching { Class.forName(className).kotlin }.getOrNull()
//}
//
//private fun replaceLastDotByDollar(input: String): String? {
//    val index = input.lastIndexOf('.')
//    return if(index != -1){
//        String(input.toCharArray().apply { set(index, '$') })
//    } else {
//        null
//    }
//
//
//}

