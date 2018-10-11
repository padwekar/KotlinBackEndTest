package com.healer.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.time.LocalDate


fun main(args: Array<String>) {
    val port = if (System.getenv("PORT").isNullOrEmpty()) 8080 else Integer.parseInt(System.getenv("PORT"))
    embeddedServer(Netty, port,module = Application::calls).start(wait = true)
}

fun Application.calls(){
    routing {
        get("/") {
            call.respondText("Hello Healer", ContentType.Text.Html)
        }

        get("/greet") {
            call.respondText("Hello Saurabh Padwekar", ContentType.Text.Html)
        }

        get("/bestdate") {
            call.respondText(LocalDate.now().toString(), ContentType.Any)
        }

    }
}

