package com.departementle_server.hugofab.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Guess(
    val departement: String,
    val distanceTo: Int,
    val direction: String
)