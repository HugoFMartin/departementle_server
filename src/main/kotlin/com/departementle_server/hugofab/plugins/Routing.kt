package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.data.datasource.DepartementDataSourceImpl
import com.departementle_server.hugofab.data.model.Departement
import com.departementle_server.hugofab.data.model.Try
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(a: DepartementDataSourceImpl) {

    routing {
        get("/daily") {
            val dailyDep = a.getDailyDepartement()
            call.respond(dailyDep)
        }
        get("/departements") {
            val depNames = a.getAllDepartementName()
            call.respond(depNames)
        }
        post("/try-daily") {
            val departementTry = call.receive<Try>()
            val respond = a.tryDepartement(departementTry.name)
            call.respondText(respond.toString(), status = HttpStatusCode.OK)
        }
    }
}
