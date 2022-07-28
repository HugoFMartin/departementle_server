package com.departementle_server.hugofab.data.utils

import kotlin.math.*

object Direction {
    const val north: String = "North"
    const val south: String = "South"
    const val east: String = "Est"
    const val west: String = "West"
    const val northEast: String = "North-East"
    const val northWest: String = "North-West"
    const val southEast: String = "South-East"
    const val southWest: String = "South-West"
}

object GeomUtils {


    fun getDistanceFromTo(lat1: Double, long1: Double, lat2: Double, long2: Double): Int {
        val theta = lat2 - lat1
        var dist = sin(long1) * sin(long2) + cos(long1) * cos(long2) * cos(theta)
        dist = acos(dist)
        dist *= 60 * 1.1515
        dist *= 1.609344
        return dist.roundToInt()
    }

    fun getDirectionFromTo(lat1: Double, long1: Double, lat2: Double, long2: Double): String {
        val dy = (lat2 - lat1)
        val dx = cos(Math.PI / 180 * lat1) * (long2 - long1)
        val angle = atan2(dy, dx)

        val dgr = (toDeg(angle))
        return this.getDirectionFromOrientation(dgr)
    }

    private fun getDirectionFromOrientation(dgr: Double): String {
        when((dgr / 22.5).roundToInt()){
            in 1..3 -> {
                return Direction.northEast
            }
            4 -> {
                return Direction.east
            }
            in 5..7 -> {
                return Direction.southEast
            }
            8 -> {
                return Direction.south
            }
            in -7..-5 -> {
                return Direction.southWest
            }
            -4 -> {
                return Direction.west
            }
            in -3..-1 -> {
                return Direction.northWest
            }
            else -> {
                return Direction.north
            }
        }
    }

    private fun toDeg(value: Double): Double {
        return (value * 180) / Math.PI
    }
}