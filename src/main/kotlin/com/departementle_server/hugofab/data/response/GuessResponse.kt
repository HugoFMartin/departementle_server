package com.departementle_server.hugofab.data.response

import com.departementle_server.hugofab.data.model.Guess
import kotlinx.serialization.Serializable

@Serializable
data class GuessResponse (
    val guess: Guess?,
    val isValid: Boolean,
    val date: Int
)