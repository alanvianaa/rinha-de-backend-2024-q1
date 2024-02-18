package com.rinha.plugins

import org.jetbrains.exposed.sql.Database

object DatabaseConnection {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost/rinha-database",
            user = "postgres",
            password = "admin",
            driver = "org.postgresql.Driver"
        )
    }
}
