package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.data.datasource.DepartementDataSourceImpl
import com.departementle_server.hugofab.data.model.Departement
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(a: DepartementDataSourceImpl) {

    routing {
        get("/get-daily") {
            val dailyDep = a.getDailyDepartement()
            call.respond(dailyDep)
        }
        get("/get-departement-name") {
            val depNames = a.getAllDepartementName()
            call.respond(depNames)
        }
    }
}
