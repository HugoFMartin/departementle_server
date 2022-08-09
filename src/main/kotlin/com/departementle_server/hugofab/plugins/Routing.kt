package com.departementle_server.hugofab.plugins

import com.departementle_server.hugofab.controllers.DepartementControllerImpl
import com.departementle_server.hugofab.data.model.GuessRequest
import com.departementle_server.hugofab.data.model.HttpResponse
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(departementDataSourceImpl: DepartementControllerImpl) {

    routing {
        /**
         * Method: GET
         * Path: /departement
         * Description: return daily departement
         * Return: DepartementDTO
         */
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
        /**
         * Method: GET
         * Path: /departements
         * Description: return list of departements name
         * Return: List<String>
         */
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
        /**
         * Method: POST
         * Path: /guess
         * Params: Object as GuessRequest
         * Description: return if the departement guessed is the same as the daily one
         * Return: GuessDTO
         */
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
