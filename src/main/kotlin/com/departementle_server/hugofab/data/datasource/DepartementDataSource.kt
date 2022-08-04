package com.departementle_server.hugofab.data.datasource

import com.departementle_server.hugofab.data.response.DailyDepartementResponse
import com.departementle_server.hugofab.data.response.GuessResponse

interface DepartementDataSource {

    suspend fun getDailyDepartement(): DailyDepartementResponse

    suspend fun getAllDepartementName(): List<String>

    suspend fun guessDepartement(departementName: String): GuessResponse
}