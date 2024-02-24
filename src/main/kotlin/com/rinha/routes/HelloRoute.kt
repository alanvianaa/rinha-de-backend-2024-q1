package com.rinha.routes

import io.ktor.server.application.call
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get


fun Route.helloRoute() {
    get("/") {
        call.respondText("Hello World!")
    }
    swaggerUI(path = "swagger", swaggerFile = "documentation.yaml")
}
