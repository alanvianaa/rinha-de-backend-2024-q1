package com.rinha.plugins

import com.rinha.routes.helloRoute
import com.rinha.routes.clienteRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureRoutings() {
    routing {
        helloRoute()
        clienteRoute()
    }
}