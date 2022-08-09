package com.departementle_server.hugofab.controllers

import com.departementle_server.hugofab.data.model.DepartementDTO
import com.departementle_server.hugofab.data.model.GuessDTO
import com.departementle_server.hugofab.data.model.GuessRequest
import com.departementle_server.hugofab.data.model.HttpResponse

interface DepartementController {

    suspend fun getDailyDepartement(): HttpResponse<DepartementDTO>

    suspend fun getAllDepartementName(): HttpResponse<List<String>>

    suspend fun guessDepartement(requestBody: GuessRequest): HttpResponse<GuessDTO>
}