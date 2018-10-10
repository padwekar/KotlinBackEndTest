package com.healer.server

import java.lang.reflect.Array.get
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
        embeddedServer(Netty, 8080,watchPaths = listOf("Server/src/main/kotlin"),module = Application::calls).start(wait = true)
}


fun Application.calls(){

    routing {
        get("/") {
            call.respondText("Hello Healer", ContentType.Text.Html)
        }

        get("/same") {
            call.respondText("Same same", ContentType.Application.Json)
        }
    }
}

