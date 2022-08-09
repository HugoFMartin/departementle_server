package com.departementle_server.hugofab

import com.departementle_server.hugofab.controllers.DepartementControllerImpl
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.departementle_server.hugofab.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main() {
    val client = KMongo.createClient().coroutine
    val database = client.getDatabase("departement_db")

    val departementDataSourceImpl = DepartementControllerImpl(database)

    embeddedServer(Netty, port = 8080, host = "192.168.1.85") {
        configureRouting(departementDataSourceImpl)
        configureSerialization()
        install(CORS) {
            allowHost("127.0.0.1:4200")
            allowHeader(HttpHeaders.ContentType)
        }
    }.start(wait = true)
}
