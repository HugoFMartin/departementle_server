package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.data.datasource.DepartementDataSourceImpl
import com.departementle_server.hugofab.data.model.Departement
import com.departementle_server.hugofab.data.model.Try
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
        post("/try-daily") {
            val departementTry = call.receive<Try>()
            val respond = departementDataSourceImpl.tryDepartement(departementTry.name)
            call.respondText(respond.toString(), status = HttpStatusCode.OK)
        }
    }
}
