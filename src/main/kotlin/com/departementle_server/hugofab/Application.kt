package com.departementle_server.hugofab

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.departementle_server.hugofab.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
