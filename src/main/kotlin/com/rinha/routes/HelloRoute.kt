package com.rinha.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.helloRoute() {
    get("/") {
        call.respondText("Hello World!")
    }
}
