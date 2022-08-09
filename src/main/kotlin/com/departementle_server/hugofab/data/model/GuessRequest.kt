package com.departementle_server.hugofab.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GuessRequest(
    val departementName: String,
    val id: Int
)