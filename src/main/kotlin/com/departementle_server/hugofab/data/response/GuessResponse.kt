package com.departementle_server.hugofab.data.response

import kotlinx.serialization.Serializable

@Serializable
data class GuessResponse (
    val guessId: Int,
    val departement: String,
    val isValid: Boolean,
    val distanceTo: Int,
    val direction: String
)