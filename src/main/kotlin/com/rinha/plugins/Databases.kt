package com.rinha.plugins

import io.ktor.server.application.ApplicationEnvironment
import org.jetbrains.exposed.sql.Database

object DatabaseConnection {
    fun init(environment: ApplicationEnvironment) {

        val hostname = environment.config.propertyOrNull("database.hostname")?.getString() ?: "localhost"
        val database = environment.config.propertyOrNull("database.database")?.getString() ?: "rinha-database"
        val user = environment.config.propertyOrNull("database.user")?.getString() ?: "postgres"
        val password = environment.config.propertyOrNull("database.password")?.getString() ?: "admin"

        Database.connect(
            url = "jdbc:postgresql://$hostname/$database",
            user = user,
            password = password,
            driver = "org.postgresql.Driver"
        )
    }
}
