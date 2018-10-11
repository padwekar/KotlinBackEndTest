package com.healer.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.text.DateFormat
import java.time.LocalDate

class Person(var id : Int , var name : String)

fun main(args: Array<String>) {
    val port = if (System.getenv("PORT").isNullOrEmpty()) 8080 else Integer.parseInt(System.getenv("PORT"))
    embeddedServer(Netty, port,module = Application::calls).start(wait = true)
}

fun Application.calls(){


    install(ContentNegotiation){
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    routing {
        get("/") {
            call.respondText("Welcome to Test Server", ContentType.Text.Html)
        }

        get("/greet") {
            call.respondText("Good morning", ContentType.Text.Html)
        }

        get("/date") {
            call.respondText(LocalDate.now().toString(), ContentType.Any)
        }

        get("/urlinfo"){
            call.respondText(call.request.toString())
        }

        //Reading the path using call.parameters
        /*
            Request : http://localhost:8080/personinfo/1/2
            Response : 1  2
         */

        get("/personinfo/{id}/{subid}"){ //Eg.  returns 1 2 //PATH PARAMETERS
            call.respond("${call.parameters["id"]!!}  ${call.parameters["subid"]}")
        }

        //Reading query value using request.queryParameters
        /*
            Request : http://localhost:8080/persondata?id=1
            Response : 1  2
         */

        get("/persondata"){
            val id = call.request.queryParameters["id"]?.toInt()  //Eg.
            call.respond(Person(id ?: 0,"Healer"))
        }

        //Post Request
        /*
            Example
            Request : http://localhost:8080/saveperson
            Body : {"id":1,"name":"Awlis"}
            Response : {"id":1,"name":"Awlis"}
         */
        post("saveperson"){
            val person = call.receive<Person>()  //Receive the object
            call.respond(person) //Respond with the object
        }

    }
}

