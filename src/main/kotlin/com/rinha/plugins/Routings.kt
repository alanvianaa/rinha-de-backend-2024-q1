package com.rinha.plugins

import com.rinha.routes.helloRoute
import com.rinha.routes.clienteRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing


fun Application.configureRoutings() {
    routing {
        helloRoute()
        clienteRoute()
    }
}