package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.controllers.DepartementDataSourceImpl
import com.departementle_server.hugofab.data.model.GuessRequest
import com.departementle_server.hugofab.data.model.HttpResponse
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(departementDataSourceImpl: DepartementDataSourceImpl) {

    routing {
        get("/departement") {
            when(val respond = departementDataSourceImpl.getDailyDepartement()) {
                is HttpResponse.Success -> {
                    call.respond(status = HttpStatusCode.OK,respond.data!!)
                }
                is HttpResponse.Error -> {
                    call.respondText(respond.msg!!, status = HttpStatusCode.InternalServerError)
                }
            }
        }
        get("/departements") {
            when(val respond = departementDataSourceImpl.getAllDepartementName()) {
                is HttpResponse.Success -> {
                    call.respond(status = HttpStatusCode.OK,respond.data!!)
                }
                is HttpResponse.Error -> {
                    call.respondText(respond.msg!!, status = HttpStatusCode.InternalServerError)
                }
            }
        }

        post("/guess") {
            try {
                val departementGuess = call.receive<GuessRequest>()
                when(val respond = departementDataSourceImpl.guessDepartement(departementGuess)) {
                    is HttpResponse.Success -> {
                        call.respond(status = HttpStatusCode.OK,respond.data!!)
                    }
                    is HttpResponse.Error -> {
                        call.respondText(respond.msg!!, status = HttpStatusCode.NotFound)
                    }
                }
            } catch (err: Throwable) {
                call.respondText(err.toString(), status = HttpStatusCode.UnprocessableEntity)
            }

        }
    }
}
