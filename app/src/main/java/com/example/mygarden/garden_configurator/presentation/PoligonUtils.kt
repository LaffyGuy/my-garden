package com.example.mygarden.garden_configurator.presentation

import androidx.compose.ui.geometry.Offset
import com.google.gson.Gson
import kotlin.math.abs

fun calculatePolygonArea(points: List<Offset>): Double {
    var sum = 0.0
    for (i in points.indices) {
        val j = (i + 1) % points.size
        sum += (points[i].x * points[j].y - points[j].x * points[i].y)
    }
    return abs(sum / 2.0)
}

fun isPointInPolygon(point: Offset, polygon: List<Offset>): Boolean {
    var inside = false
    var j = polygon.lastIndex
    for (i in polygon.indices) {
        val xi = polygon[i].x
        val yi = polygon[i].y
        val xj = polygon[j].x
        val yj = polygon[j].y

        val intersect = (yi > point.y) != (yj > point.y) &&
                (point.x < (xj - xi) * (point.y - yi) / (yj - yi + 0.00001) + xi)
        if (intersect) inside = !inside
        j = i
    }
    return inside
}

val gson = Gson()

fun pointsToJson(points: List<Offset>): String {
    return gson.toJson(points.map { PointData(it.x, it.y) })
}

data class PointData(val x: Float, val y: Float)