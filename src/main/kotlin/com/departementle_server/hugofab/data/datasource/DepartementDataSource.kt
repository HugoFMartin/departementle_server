package com.departementle_server.hugofab.data.datasource

import com.departementle_server.hugofab.data.model.DepartementDTO
import com.departementle_server.hugofab.data.response.GuessResponse

interface DepartementDataSource {

    suspend fun getDailyDepartementDTO(): DepartementDTO

    suspend fun getAllDepartementName(): List<String>

    suspend fun guessDepartement(departementName: String): GuessResponse
}