package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.data.datasource.DepartementDataSourceImpl
import com.departementle_server.hugofab.data.model.GuessDepartement
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
            val departementTry = call.receive<GuessDepartement>()
            val respond = departementDataSourceImpl.guessDepartement(departementTry.guessDepartementName)
            if (respond) {
                call.respondText("guess right", status = HttpStatusCode.OK)
            } else {
                call.respondText("guess false", status = HttpStatusCode.Forbidden)
            }
        }
    }
}
