package com.departementle_server.hugofab.data.datasource

import com.departementle_server.hugofab.data.model.Departement

interface DepartementDataSource {

    suspend fun getDailyDepartement(): Departement

    suspend fun getAllDepartementName(): List<String>

    suspend fun guessDepartement(departementName: String): Boolean
}