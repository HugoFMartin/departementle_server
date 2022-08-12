package com.departementle_server.hugofab.utils

import kotlin.math.*

object Direction {
    const val north: String = "N"
    const val south: String = "S"
    const val east: String = "E"
    const val west: String = "W"
    const val northEast: String = "NE"
    const val northWest: String = "NW"
    const val southEast: String = "SE"
    const val southWest: String = "SW"
    const val guessed: String = "Guessed"
}

object GeomUtils {


    fun getDistanceFromTo(lat1: Double, long1: Double, lat2: Double, long2: Double): Int {
        //val theta = lat2 - lat1
        //var dist = sin(long1) * sin(long2) + cos(long1) * cos(long2) * cos(theta)
        //dist = acos(dist)
        //dist *= 60 * 1.1515
        //dist *= 1.609344
        //return dist.roundToInt()
        
        val r = 6371; // Radius of the earth in km
        val dLat = toRad(lat2-lat1)
        val dLon = toRad(long2-long1)
        val a =
            sin(dLat/2) * sin(dLat/2) +
                    cos(toRad(lat1)) * cos(toRad(lat2)) *
                    sin(dLon/2) * sin(dLon/2)
        val c = 2 * atan2(sqrt(a), sqrt(1-a))
        val d = r * c
        return d.roundToInt()
    }

    fun getDirectionFromTo(lat1: Double, long1: Double, lat2: Double, long2: Double): String {
        val dy = (lat2 - lat1)
        val dx = cos(Math.PI / 180 * lat1) * (long2 - long1)
        val angle = atan2(dy, dx)

        val dgr = (toDeg(angle))
        return getDirectionFromOrientation(dgr)
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
    
    private fun toRad(deg: Double): Double {
        return deg * (Math.PI/180)
    }
}
