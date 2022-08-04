package com.departementle_server.hugofab.data.response

import com.departementle_server.hugofab.data.model.DepartementDTO
import kotlinx.serialization.Serializable

@Serializable
data class DailyDepartementResponse(
    val departement: DepartementDTO,
    val date: Int
)