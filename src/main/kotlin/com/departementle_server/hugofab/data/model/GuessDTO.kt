package com.departementle_server.hugofab.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GuessDTO (
    val guess: Guess?,
    val isValid: Boolean,
    val date: Int
)