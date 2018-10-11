package com.healer.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
        embeddedServer(Netty, 8080,module = Application::calls).start(wait = true)
}

fun Application.calls(){
    routing {
        get("/") {
            call.respondText("Hello Healer", ContentType.Text.Html)
        }
    }
}

