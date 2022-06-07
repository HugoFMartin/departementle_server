package com.departementle_server.hugofab

import com.departementle_server.hugofab.data.datasource.DepartementDataSourceImpl
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.departementle_server.hugofab.plugins.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main() {
    val client = KMongo.createClient().coroutine
    val database = client.getDatabase("departement_db")

    val a = DepartementDataSourceImpl(database)

    embeddedServer(Netty, port = 8080, host = "192.168.1.85") {


        configureRouting(a)
        configureSerialization()
    }.start(wait = true)
}
