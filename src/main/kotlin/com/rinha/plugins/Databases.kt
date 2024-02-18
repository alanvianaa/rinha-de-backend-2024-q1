package com.rinha.plugins

import io.ktor.server.application.ApplicationEnvironment
import org.jetbrains.exposed.sql.Database

object DatabaseConnection {
    fun init(environment: ApplicationEnvironment) {

        val hostname = environment.config.propertyOrNull("database.hostname")?.getString() ?: throw Exception("Enviroment error")
        val database = environment.config.propertyOrNull("database.database")?.getString() ?: throw Exception("Enviroment error")
        val user = environment.config.propertyOrNull("database.user")?.getString() ?: throw Exception("Enviroment error")
        val password = environment.config.propertyOrNull("database.password")?.getString() ?: throw Exception("Enviroment error")

        Database.connect(
            url = "jdbc:postgresql://$hostname/$database",
            user = user,
            password = password,
            driver = "org.postgresql.Driver"
        )
    }
}
