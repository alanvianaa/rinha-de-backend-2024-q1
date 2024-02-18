package com.rinha

import com.rinha.plugins.DatabaseConnection
import com.rinha.plugins.configureRoutings
import com.rinha.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    DatabaseConnection.init(environment)
    configureSerialization()
    configureRoutings()
}
