package com.rinha

import com.rinha.plugins.DatabaseConnection
import com.rinha.plugins.configureRoutings
import com.rinha.plugins.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseConnection.init(environment)
    configureSerialization()
    configureRoutings()
}
