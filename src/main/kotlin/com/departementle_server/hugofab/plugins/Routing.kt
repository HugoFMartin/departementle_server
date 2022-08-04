package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.data.datasource.DepartementDataSourceImpl
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(departementDataSourceImpl: DepartementDataSourceImpl) {

    routing {
        get("/daily") {
            val dailyDep = departementDataSourceImpl.getDailyDepartement()
            call.respond(dailyDep)
        }
        get("/departements") {
            val depNames = departementDataSourceImpl.getAllDepartementName()
            call.respond(depNames)
        }
        post("/guess") {
            val departementGuess = call.receive<String>()
            val respond = departementDataSourceImpl.guessDepartement(departementGuess)
            call.respond(status = HttpStatusCode.OK,respond)
        }
    }
}
