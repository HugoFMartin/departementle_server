package com.departementle_server.hugofab.data.datasource

import com.departementle_server.hugofab.data.model.DepartementDTO

interface DepartementDataSource {

    suspend fun getDailyDepartementDTO(): DepartementDTO

    suspend fun getAllDepartementName(): List<String>

    suspend fun guessDepartement(departementName: String): Boolean
}