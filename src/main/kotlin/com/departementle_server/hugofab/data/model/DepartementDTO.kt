package com.departementle_server.hugofab.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DepartementDTO(
    val name: String,
    val img: String,
    val id: Int
)